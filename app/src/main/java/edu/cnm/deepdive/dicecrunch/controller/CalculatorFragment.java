package edu.cnm.deepdive.dicecrunch.controller;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.dicecrunch.R;
import edu.cnm.deepdive.dicecrunch.databinding.FragmentCalculatorBinding;
import edu.cnm.deepdive.dicecrunch.model.type.Operator;
import edu.cnm.deepdive.dicecrunch.viewmodel.CalculatorViewModel;

/**
 * Functionality to control the Calculator UI fragment.
 */
public class CalculatorFragment extends Fragment {

  private CalculatorViewModel calculatorViewModel;
  private FragmentCalculatorBinding binding;

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
    getLifecycle().addObserver(calculatorViewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    // FIXME Formula is not being stored correctly in the ViewModel. NoSuchElementException, Parser 123
    calculatorViewModel.getFormula().observe(lifecycleOwner,
        (formula) -> binding.formula.setText(formula));
    calculatorViewModel.getResult().observe(lifecycleOwner,
        (result) -> binding.rollResult.setText(result));
    setButtonClickListeners();
    getLifecycle().addObserver(calculatorViewModel);
  }

  private void addToFormula(String append) {
    calculatorViewModel.appendToFormula(append);
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
    binding.dXSided.setOnClickListener((v) -> addToFormula(Operator.DICE_ROLL.getSymbol()));
    // special function operators
    binding.dropLowest.setOnClickListener((v) -> addToFormula(
        Operator.DROP_LOWEST.getSymbol() + Operator.LEFT_PARENTHESIS.getSymbol()));
    binding.dropHighest.setOnClickListener((v) -> addToFormula(
        Operator.DROP_HIGHEST.getSymbol() + Operator.LEFT_PARENTHESIS.getSymbol()));
    binding.rightParenthesis
        .setOnClickListener((v) -> addToFormula(")"));
    binding.leftParenthesis
        .setOnClickListener((v) -> addToFormula(Operator.LEFT_PARENTHESIS.getSymbol()));
    // Delete and clear
    binding.delete.setOnClickListener((v) -> calculatorViewModel.backspace());
    binding.clear.setOnClickListener((v) -> calculatorViewModel.clearFormula());
    // Roll button
    binding.roll.setOnClickListener((v) -> rollClicked());
    binding.trace.setOnClickListener((v) -> showTrace());
  }

  private void rollClicked() {
    // Parse and evaluate the formula in the LiveData field.
    calculatorViewModel.evaluate();
    // Open the results in the destination screen selected from Settings screen.
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    String destinationName = preferences
        .getString("roll_destination_key", "navigation_calculator");
    Resources res = getResources();
    int destinationId = res.getIdentifier(destinationName, "id", getActivity().getPackageName());
    if (destinationId != 0 && destinationId != R.id.navigation_calculator) {
      Navigation.findNavController(binding.getRoot()).navigate(destinationId);
    }
  }

  private void showTrace() {
    String trace = String.join("\n", calculatorViewModel.getTrace().getValue());
    new AlertDialog.Builder(getContext())
        .setMessage(trace)
        .setPositiveButton(android.R.string.ok, (dlg, w) -> {})
        .create()
        .show();
  }

}