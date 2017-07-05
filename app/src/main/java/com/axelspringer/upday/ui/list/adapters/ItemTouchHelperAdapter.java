package com.axelspringer.upday.ui.list.adapters;

/**
 * Created by damien on 7/5/17.
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
