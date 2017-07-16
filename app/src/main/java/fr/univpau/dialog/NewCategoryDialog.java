package fr.univpau.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.univpau.todolist.R;

public class NewCategoryDialog extends DialogFragment {

    public interface NewCategoryDialogListener {
        public void onConfirmNewCategory(DialogFragment dialog, String title);
        public void onCancelNewCategory(DialogFragment dialog);
    }

    NewCategoryDialogListener _listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _listener = (NewCategoryDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ConfirmDeleteDialogListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_new_category, container, false);

        final EditText titleInput = (EditText)v.findViewById(R.id.nameEditText);

        Button confirmButton = (Button)v.findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _listener.onConfirmNewCategory(NewCategoryDialog.this, titleInput.getText().toString());
                dismiss();
            }
        });

        Button cancelButton = (Button)v.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _listener.onCancelNewCategory(NewCategoryDialog.this);
                dismiss();
            }
        });

        return v;
    }

}
