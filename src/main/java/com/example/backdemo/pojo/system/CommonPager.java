package com.example.backdemo.pojo.system;

import com.baomidou.mybatisplus.plugins.Page;

public class CommonPager<T> extends Page {
    private boolean export;
    private boolean exportAll;
    public boolean isExport() {
        return export;
    }

    public void setExport(boolean export) {
        this.export = export;
    }

    public boolean isExportAll() {
        return exportAll;
    }

    public void setExportAll(boolean exportAll) {
        this.exportAll = exportAll;
    }
}
