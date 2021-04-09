package edu.cnm.deepdive.dicecrunch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.dicecrunch.adapter.HistoryAdapter.Holder;
import edu.cnm.deepdive.dicecrunch.databinding.ItemHistoryBinding;
import edu.cnm.deepdive.dicecrunch.model.entity.History;
import java.util.List;

/**
 * Manages populating the scrollable history of dice rolls.
 */
public class HistoryAdapter extends RecyclerView.Adapter<Holder> {

  private final List<History> historyOfRolls;
  private final LayoutInflater inflater;
  private final Context context;
  private final RerollHandler handler;

  /**
   * Creates a HistoryAdapter.
   *
   * @param context Access the current state of the application.
   * @param historyOfRolls A list of all dice formula and their results.
   * @param handler
   */
  public HistoryAdapter(@NonNull Context context, List<History> historyOfRolls,
      RerollHandler handler) {
    this.context = context;
    this.historyOfRolls = historyOfRolls;
    inflater = LayoutInflater.from(context);
    this.handler = handler;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new Holder(ItemHistoryBinding.inflate(inflater, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return historyOfRolls.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemHistoryBinding binding;

    public Holder(@NonNull ItemHistoryBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      History historyRoll = historyOfRolls.get(position);
      binding.formula.setText(historyRoll.getFormula());
      binding.rollResult.setText((historyRoll.getResult()));
      binding.trace.setOnClickListener((v) -> showTrace(historyRoll.getTrace()));
      binding.reroll.setOnClickListener((v) -> handler.reroll(historyRoll.getFormula()));
    }

    private void showTrace(String trace) {
      new AlertDialog.Builder(context)
          .setMessage(trace)
          .setPositiveButton(android.R.string.ok, (dlg, w) -> {})
          .create()
          .show();
    }

  }

  /**
   *
   */
  public interface RerollHandler {

    void reroll(String expression);

  }

}
