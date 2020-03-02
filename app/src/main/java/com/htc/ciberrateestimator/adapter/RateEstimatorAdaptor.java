package com.htc.ciberrateestimator.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.htc.ciberrateestimator.R;
import com.htc.ciberrateestimator.listeners.EditTextListeners;
import com.htc.ciberrateestimator.listeners.RateEstimateDeleteListeners;
import com.htc.ciberrateestimator.model.RateEstimatorModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RateEstimatorAdaptor extends RecyclerView.Adapter<RateEstimatorAdaptor.RateEstimatorViewHolder> {

    private Context mCtx;
    private List<RateEstimatorModel> rateEstimatorModelList = new ArrayList<>();
    private RateEstimateDeleteListeners rateEstimateDeleteListeners;
    private int removedPosition = 0;
    private int selectedRate = 0;
    private Double fixedGP;
    private long fixedEstimatedHours;

    private EditTextListeners edittextListeners;

    private RateEstimatorModel removeRateEstimatorModel;

    public RateEstimatorAdaptor(Context mCtx, List<RateEstimatorModel> rateEstimatorModelList, Double fixedGP, long fixedEstimatedHours) {
        this.mCtx = mCtx;
        this.rateEstimatorModelList = rateEstimatorModelList;
        this.fixedEstimatedHours = fixedEstimatedHours;
        this.fixedGP = fixedGP;
    }

    public void setSelectDeleteRateEstimatorListener(RateEstimateDeleteListeners rateEstimateDeleteListeners) {
        this.rateEstimateDeleteListeners = rateEstimateDeleteListeners;
    }

    public void setGpValueListener(EditTextListeners edittextListeners) {
        this.edittextListeners = edittextListeners;
    }

    public void setSelectedRate(int selectedRate) {
        this.selectedRate = selectedRate;
        notifyDataSetChanged();
    }


    public List<RateEstimatorModel> getRateModelList() {
        return rateEstimatorModelList;
    }

    @NonNull
    @Override
    public RateEstimatorAdaptor.RateEstimatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_no_curve, parent, false);
        return new RateEstimatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RateEstimatorAdaptor.RateEstimatorViewHolder holder, final int position) {

        Typeface bold = Typeface.createFromAsset(mCtx.getAssets(), "font/opensans_bold.ttf");
        Typeface normal = Typeface.createFromAsset(mCtx.getAssets(), "font/opensans_regular.ttf");

        holder.tvResource.setText("Resource " + (position + 1));


        if (selectedRate == 0) {
//            holder.tvMinimumBillRate.setText(Html.fromHtml("<sup><small>$</small></sup>" + new DecimalFormat("#0.00").format(rateEstimatorModelList.get(position).getMinBillRate())));
            holder.tvMinimumBillRate.setText(Html.fromHtml("$" + new DecimalFormat("###,###,###,##0.00").format(rateEstimatorModelList.get(position).getMinBillRate())));
        } else if (selectedRate == 1) {
//            holder.tvMinimumBillRate.setText(Html.fromHtml("<sup><small>$</small></sup>" + new DecimalFormat("#0.00").format(rateEstimatorModelList.get(position).getMinBillRateBySalary())));
            holder.tvMinimumBillRate.setText(Html.fromHtml("$" + new DecimalFormat("###,###,###,##0.00").format(rateEstimatorModelList.get(position).getMinBillRateBySalary())));
        }

        holder.ivDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateEstimateDeleteListeners.onSelectRateEstimatedItemClick(view, rateEstimatorModelList.get(position), holder.getAdapterPosition());
            }
        });
        holder.llArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateEstimateDeleteListeners.onSelectRateEstimatedItemClick(view, rateEstimatorModelList.get(position), holder.getLayoutPosition());
            }
        });
        holder.llJobTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateEstimateDeleteListeners.onSelectRateEstimatedItemClick(view, rateEstimatorModelList.get(position), holder.getLayoutPosition());
            }
        });
        holder.llJobLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateEstimateDeleteListeners.onSelectRateEstimatedItemClick(view, rateEstimatorModelList.get(position), holder.getLayoutPosition());
            }
        });


        holder.edGp.setText(new DecimalFormat("#0.00").format(rateEstimatorModelList.get(position).getgP()));
        holder.edGp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty())
                    rateEstimatorModelList.get(holder.getAdapterPosition()).setgP(Double.parseDouble(charSequence.toString()));
                else
                    rateEstimatorModelList.get(holder.getAdapterPosition()).setgP(fixedGP);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                try {
////                    Log.d("Percentage", "input: " + s);
//                    if (Double.parseDouble(s.toString()) > 100.00)
//                        s.replace(0, s.length(), "100.00");
//                } catch (NumberFormatException nfe) {
//
//                }

            }
        });

        holder.edEstimatedHours.setText("" + rateEstimatorModelList.get(position).getEstimatedHours());
        holder.edEstimatedHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty())
                    rateEstimatorModelList.get(holder.getAdapterPosition()).setEstimatedHours(Integer.parseInt(charSequence.toString()));
                else
                    rateEstimatorModelList.get(holder.getAdapterPosition()).setEstimatedHours(fixedEstimatedHours);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        holder.edGp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edittextListeners.editTextValueChanged(v, position);
                    holder.edGp.setCursorVisible(false);
                }
                return false;
            }
        });

        holder.edGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.edGp.setCursorVisible(true);
            }
        });

//        holder.edGp.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                holder.edGp.setSelection(holder.edGp.length());
//                return false;
//            }
//        });

        holder.edGp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    holder.edGp.setSelection(holder.edGp.length());
                }
            }
        });

//        holder.edGp.setFilters(new InputFilter[]{new InputFilterMinMax("1.00", "100.00")});

        holder.edEstimatedHours.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edittextListeners.editTextValueChanged(v, position);
                    holder.edEstimatedHours.setCursorVisible(false);
                }
                return false;
            }
        });

        holder.edEstimatedHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.edEstimatedHours.setCursorVisible(true);
            }
        });


//        holder.edEstimatedHours.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                return false;
//            }
//        });

        holder.edEstimatedHours.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    holder.edEstimatedHours.setSelection(holder.edEstimatedHours.length());
                }
            }
        });


        if (rateEstimatorModelList.get(position).getAreaModel() != null) {
            holder.tvArea.setTypeface(bold);
            holder.tvArea.setText("Area " + rateEstimatorModelList.get(position).getAreaModel().getId());
            holder.tvArea.setTextColor(mCtx.getResources().getColor(R.color.textColorSelected));
        } else {
            holder.tvArea.setTypeface(normal);
            holder.tvArea.setText("Area");
            holder.tvArea.setTextColor(mCtx.getResources().getColor(R.color.textColorNoSelected));
        }

        if (rateEstimatorModelList.get(position).getJobLevelModel() != null) {
            holder.tvJobLevel.setTypeface(bold);
            holder.tvJobLevel.setText("" + rateEstimatorModelList.get(position).getJobLevelModel().getJobLevelName());
            holder.tvJobLevel.setTextColor(mCtx.getResources().getColor(R.color.textColorSelected));
        } else {
            holder.tvJobLevel.setTypeface(normal);
            holder.tvJobLevel.setText("Job Level");
            holder.tvJobLevel.setTextColor(mCtx.getResources().getColor(R.color.textColorNoSelected));
        }

        if (rateEstimatorModelList.get(position).getJobTitleModel() != null) {
            holder.tvJobTitle.setTypeface(bold);
            holder.tvJobTitle.setText("" + rateEstimatorModelList.get(position).getJobTitleModel().getJobTitleName());
            holder.tvJobTitle.setTextColor(mCtx.getResources().getColor(R.color.textColorSelected));
        } else {
            holder.tvJobTitle.setTypeface(normal);
            holder.tvJobTitle.setText("Job Title");
            holder.tvJobTitle.setTextColor(mCtx.getResources().getColor(R.color.textColorNoSelected));
        }

    }

    public void removeRateElement(int position) {
        removeRateEstimatorModel = rateEstimatorModelList.get(position);
        removedPosition = position;
        rateEstimatorModelList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return rateEstimatorModelList.size();
    }

    class RateEstimatorViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDeleteCard;
        private LinearLayout llJobTitle;
        private LinearLayout llJobLevel;
        private LinearLayout llArea;
        private EditText edGp;
        private EditText edEstimatedHours;
        private TextView tvResource;
        private TextView tvArea;
        private TextView tvJobLevel;
        private TextView tvJobTitle;
        private TextView tvMinimumBillRate;

        RateEstimatorViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDeleteCard = itemView.findViewById(R.id.ivDeleteCard);
            llArea = itemView.findViewById(R.id.llArea);
            llJobTitle = itemView.findViewById(R.id.llJobTitle);
            llJobLevel = itemView.findViewById(R.id.llJobLevel);
            tvResource = itemView.findViewById(R.id.tvResource);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvJobLevel = itemView.findViewById(R.id.tvJobLevel);
            tvJobTitle = itemView.findViewById(R.id.tvJobTitle);
            tvMinimumBillRate = itemView.findViewById(R.id.tvMinimumBillRate);

            edGp = itemView.findViewById(R.id.edGp);
            edEstimatedHours = itemView.findViewById(R.id.edEstimatedHours);

        }
    }

}
