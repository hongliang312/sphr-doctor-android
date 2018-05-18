package com.lightheart.sphr.doctor.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class PanelSection extends SectionEntity<PanelsModel> {
    private boolean isMore;

    public PanelSection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public PanelSection(PanelsModel t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
