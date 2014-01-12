package ch.citux.twitchdroid.ui.fragments;

import android.app.Activity;
import android.support.v4.app.ListFragment;

import ch.citux.twitchdroid.data.worker.TDCallback;
import ch.citux.twitchdroid.ui.TDActivity;
import ch.citux.twitchdroid.ui.dialogs.ErrorDialogFragment;

public abstract class TDFragment<Result> extends ListFragment implements TDCallback<Result> {

    private TDActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TDActivity) {
            this.activity = (TDActivity) activity;
        } else {
            throw new IllegalStateException("TDFragment must be attached to a TDActivity.");
        }
    }

    public void startLoading() {
        if (activity != null) {
            activity.startLoading();
        }
    }

    public void stopLoading() {
        if (activity != null) {
            activity.stopLoading();
        }
    }

    @Override
    public void onError(String title, String message) {
        ErrorDialogFragment.ErrorDialogFragmentBuilder builder = new ErrorDialogFragment.ErrorDialogFragmentBuilder(getActivity());
        builder.setTitle(title).setMessage(message).show();
    }

    public void loadData() {
    }

    public void refreshData() {
        loadData();
    }

    public TDActivity getTDActivity() {
        return activity;
    }
}
