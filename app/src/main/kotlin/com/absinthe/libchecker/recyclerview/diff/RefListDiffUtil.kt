package com.absinthe.libchecker.recyclerview.diff

import androidx.recyclerview.widget.DiffUtil
import com.absinthe.libchecker.bean.LibReference
import com.chad.library.adapter.base.entity.node.BaseNode

class RefListDiffUtil : DiffUtil.ItemCallback<BaseNode>() {

  override fun areItemsTheSame(oldItem: BaseNode, newItem: BaseNode): Boolean {
    return oldItem.hashCode() == newItem.hashCode()
  }

  override fun areContentsTheSame(oldItem: BaseNode, newItem: BaseNode): Boolean {
    if (oldItem is LibReference && newItem is LibReference) {
      return oldItem.libName == newItem.libName &&
        oldItem.referredList.size == newItem.referredList.size &&
        oldItem.chip == newItem.chip &&
        oldItem.type == newItem.type
    } else {
      return oldItem == newItem
    }
  }
}
