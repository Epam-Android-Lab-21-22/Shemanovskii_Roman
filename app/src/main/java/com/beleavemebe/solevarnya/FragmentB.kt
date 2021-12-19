package com.beleavemebe.solevarnya

class FragmentB : BaseFragment(R.layout.fragment_b) {
    override fun deepenSelf(stackPosition: Int) =
        deepen<FragmentB>(stackPosition)
}
