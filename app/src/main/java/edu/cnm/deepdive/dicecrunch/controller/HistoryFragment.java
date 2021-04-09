package edu.cnm.deepdive.dicecrunch.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.dicecrunch.adapter.HistoryAdapter;
import edu.cnm.deepdive.dicecrunch.databinding.FragmentHistoryBinding;
import edu.cnm.deepdive.dicecrunch.viewmodel.CalculatorViewModel;

public class HistoryFragment extends Fragment {

  private FragmentHistoryBinding binding;
  private CalculatorViewModel viewModel;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentHistoryBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
    viewModel.getHistory().observe(getViewLifecycleOwner(), (historyOfRolls) -> {
      HistoryAdapter adapter = new HistoryAdapter(getContext(), historyOfRolls, (formula) -> {
        // TODO Invoke parser.
        viewModel.clearFormula();
        viewModel.appendToFormula(formula);
        viewModel.evaluate();
      });
      binding.rolls.setAdapter(adapter);
    });
  }

}