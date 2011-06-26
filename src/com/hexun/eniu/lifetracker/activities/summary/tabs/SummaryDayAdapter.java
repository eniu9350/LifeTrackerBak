package com.hexun.eniu.lifetracker.activities.summary.tabs;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.entity.Target;
import com.hexun.eniu.lifetracker.entity.TargetEntry;

public class SummaryDayAdapter extends BaseAdapter {
	private Context context;

	private List<Target> targetList;

	public SummaryDayAdapter(Context context, List<Target> targetList) {
		this.context = context;
		this.targetList = targetList;
	}

	public int getCount() {
		return targetList.size();
	}

	public Object getItem(int position) {
		return targetList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup viewGroup) {
		Target t = targetList.get(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.summary_day_row, null);
		}
		TextView tvTargetName = (TextView) convertView
				.findViewById(R.id.SummaryDay_row_tvTargetName);
		tvTargetName.setText(t.getName());

		TextView tvCreated = (TextView) convertView
				.findViewById(R.id.SummaryDay_row_tvCreated);
		tvCreated.setText(t.getCreated().toLocaleString());

		TextView tvSpec = (TextView) convertView
				.findViewById(R.id.SummaryDay_row_tvSpec);
		List<TargetEntry> teList = t.getTes();
		String strTeList = "";
		for (TargetEntry te : teList) {
			strTeList += (te.getStopTime() - te.getStartTime()) / 1000;
		}
		tvSpec.setText(strTeList);

		// btnRemove.setId(position);

		return convertView;
	}

}
