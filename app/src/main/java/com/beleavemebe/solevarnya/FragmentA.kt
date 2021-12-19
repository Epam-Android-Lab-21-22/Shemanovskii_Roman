package com.beleavemebe.solevarnya

class FragmentA : BaseFragment(R.layout.fragment_a) {
    override fun deepenSelf(stackPosition: Int) =
        deepen<FragmentA>(stackPosition)
}
