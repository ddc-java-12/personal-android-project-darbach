package edu.cnm.deepdive.dicecrunch.controller;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.dicecrunch.R;
import edu.cnm.deepdive.dicecrunch.databinding.FragmentCalculatorBinding;
import edu.cnm.deepdive.dicecrunch.databinding.FragmentCustomDieBinding;

/**
 * UNIMPLEMENTED: Manages the state for creating and saving Custom Dice.
 */
public class CustomDieFragment extends Fragment {

  private FragmentCustomDieBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_custom_die, container, false);
  }
}