package edu.cnm.deepdive.dicecrunch.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.dicecrunch.databinding.FragmentCalculatorBinding;
import edu.cnm.deepdive.dicecrunch.service.Parser;
import edu.cnm.deepdive.dicecrunch.viewmodel.CalculatorViewModel;


public class CalculatorFragment extends Fragment {

  private static final String ROLL_RESULT_FORMAT = "%s = %s";

  private CalculatorViewModel calculatorViewModel;
  private FragmentCalculatorBinding binding;
  private String trace;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentCalculatorBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    calculatorViewModel = new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
    setButtonClickListeners();
    getLifecycle().addObserver(calculatorViewModel);
  }

  private void addToFormula(String append) {
    String currentText = binding.formula.getText().toString();
    binding.formula.setText(String.format("%s%s", currentText, append));
  }

  private void setButtonClickListeners() {
    // number keypad
    binding.numZero.setOnClickListener((v) -> addToFormula(binding.numZero.getText().toString()));
    binding.numOne.setOnClickListener((v) -> addToFormula(binding.numOne.getText().toString()));
    binding.numTwo.setOnClickListener((v) -> addToFormula(binding.numTwo.getText().toString()));
    binding.numThree.setOnClickListener((v) -> addToFormula(binding.numThree.getText().toString()));
    binding.numFour.setOnClickListener((v) -> addToFormula(binding.numFour.getText().toString()));
    binding.numFive.setOnClickListener((v) -> addToFormula(binding.numFive.getText().toString()));
    binding.numSix.setOnClickListener((v) -> addToFormula(binding.numSix.getText().toString()));
    binding.numSeven.setOnClickListener((v) -> addToFormula(binding.numSeven.getText().toString()));
    binding.numEight.setOnClickListener((v) -> addToFormula(binding.numEight.getText().toString()));
    binding.numNine.setOnClickListener((v) -> addToFormula(binding.numNine.getText().toString()));
    // math operators
    binding.numPlus.setOnClickListener((v) -> addToFormula(binding.numPlus.getText().toString()));
    binding.numMinus.setOnClickListener((v) -> addToFormula(binding.numMinus.getText().toString()));
    binding.numMultiply
        .setOnClickListener((v) -> addToFormula(binding.numMultiply.getText().toString()));
    binding.numDivide
        .setOnClickListener((v) -> addToFormula(binding.numDivide.getText().toString()));
    // dice operators
    binding.dFour.setOnClickListener((v) -> addToFormula(binding.dFour.getText().toString()));
    binding.dSix.setOnClickListener((v) -> addToFormula(binding.dSix.getText().toString()));
    binding.dEight.setOnClickListener((v) -> addToFormula(binding.dEight.getText().toString()));
    binding.dTen.setOnClickListener((v) -> addToFormula(binding.dTen.getText().toString()));
    binding.dTwelve.setOnClickListener((v) -> addToFormula(binding.dTwelve.getText().toString()));
    binding.dTwenty.setOnClickListener((v) -> addToFormula(binding.dTwenty.getText().toString()));
    binding.dHundred.setOnClickListener((v) -> addToFormula(binding.dHundred.getText().toString()));
    binding.dXSided.setOnClickListener((v) -> addToFormula("d"));
    // special function operators
    binding.dropLowest.setOnClickListener((v) -> addToFormula("dl("));
    binding.dropHighest.setOnClickListener((v) -> addToFormula("dh("));
    binding.rightParenthesis
        .setOnClickListener((v) -> addToFormula(binding.rightParenthesis.getText().toString()));
    binding.leftParenthesis
        .setOnClickListener((v) -> addToFormula(binding.leftParenthesis.getText().toString()));
    // Delete and clear
    binding.delete.setOnClickListener((v) -> {
      String currentText = binding.formula.getText().toString();
      if (currentText.length() > 0) {
        binding.formula.setText(currentText.substring(0, currentText.length() - 1));
      }
    });
    binding.clear.setOnClickListener((v) -> {
      binding.formula.setText("");
      binding.rollResult.setText("");
    });
    // Roll button
    binding.roll.setOnClickListener((v) -> rollFormula(binding.formula.getText().toString()));
    binding.trace.setOnClickListener((v) -> showTrace(trace));
  }

  private void rollFormula(String formula) {
    // TODO Parse the string into a Deque w/shunting-yard and evaluate w/Recursive Descent Parsing.
    Parser parser = new Parser(formula);
    binding.rollResult.setText(String.format(ROLL_RESULT_FORMAT, parser.getExpression(), parser.getValue()));
    trace = parser.getTrace();
  }

  private void showTrace(String trace) {
    new AlertDialog.Builder(getContext())
        .setMessage(trace)
        .setPositiveButton(android.R.string.ok, (dlg, w) -> {})
        .create()
        .show();
  }

}