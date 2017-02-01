package fr.univpau.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import fr.univpau.todolist.R;

public class ConfirmDeleteDialog extends DialogFragment {

    public interface ConfirmDeleteDialogListener {
        public void onConfirmDelete(DialogFragment dialog);
        public void onCancelDelete(DialogFragment dialog);
    }

    ConfirmDeleteDialogListener _listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _listener = (ConfirmDeleteDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ConfirmDeleteDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_confirm_tasks_deletion)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        _listener.onConfirmDelete(ConfirmDeleteDialog.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        _listener.onCancelDelete(ConfirmDeleteDialog.this);
                    }
                });
        return builder.create();
    }

}
