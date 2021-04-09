package edu.cnm.deepdive.dicecrunch.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.dicecrunch.R;
import edu.cnm.deepdive.dicecrunch.databinding.FragmentDiceTrayBinding;
import edu.cnm.deepdive.dicecrunch.databinding.ItemDiceTrayBinding;
import edu.cnm.deepdive.dicecrunch.viewmodel.CalculatorViewModel;

/**
 * Functionality to control the Dice Tray UI fragment.
 */
public class DiceTrayFragment extends Fragment {

  private CalculatorViewModel calculatorViewModel;
  private FragmentDiceTrayBinding diceTrayBinding;
  private ItemDiceTrayBinding diceTrayItemBinding;
  private ArrayAdapter<String> diceTrayAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment.
    diceTrayBinding = FragmentDiceTrayBinding.inflate(inflater, container, false);
    // An adapter for populating the Dice Tray ListView.
    diceTrayAdapter = new ArrayAdapter<String>(
        getContext(), diceTrayItemBinding.diceTrayRoll.getId(), calculatorViewModel.getTrace().getValue());
    return diceTrayBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    calculatorViewModel = new ViewModelProvider(getActivity()).get(CalculatorViewModel.class);
    getLifecycle().addObserver(calculatorViewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    calculatorViewModel.getFormula().observe(lifecycleOwner,
        (formula) -> diceTrayBinding.formula.setText(formula));
    calculatorViewModel.getResult().observe(lifecycleOwner,
        (result) -> diceTrayBinding.rollResult.setText(result));
    setButtonClickListeners();
    getLifecycle().addObserver(calculatorViewModel);
  }

  private void setButtonClickListeners() {
    diceTrayBinding.reroll.setOnClickListener((v) -> rollClicked());
  }

  private void rollClicked() {
    // Parse and evaluate the formula in the ViewModel's LiveData field.
    calculatorViewModel.evaluate();
  }

}