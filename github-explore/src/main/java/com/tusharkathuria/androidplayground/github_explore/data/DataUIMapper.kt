package com.tusharkathuria.androidplayground.github_explore.data

import com.tusharkathuria.androidplayground.github_explore.ui.models.UIModel

open class DataModel

interface DataUIMapper<DM: DataModel, UM: UIModel> {
    fun mapToUI(dataModel: DM): UM

    fun mapToData(uiModel: UM): DM
}