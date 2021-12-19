package com.beleavemebe.solevarnya

class FragmentC : BaseFragment(R.layout.fragment_c) {
    override fun deepenSelf(stackPosition: Int) =
        deepen<FragmentC>(stackPosition)
}
