package com.beleavemebe.solevarnya.presentation.model

import androidx.annotation.StringRes
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.enums.Program

enum class ProgramResourceEnum(
    @StringRes val stringResId: Int
) {
    SE(R.string.program_se),
    FM(R.string.program_fm),
    AMI(R.string.program_ami),
    LWR(R.string.program_lwr),
    EC(R.string.program_ec),
    BI(R.string.program_bi)
    ;

    companion object {
        fun from(program: Program): ProgramResourceEnum =
            when (program) {
                Program.SE -> SE
                Program.FM -> FM
                Program.AMI -> AMI
                Program.LWR -> LWR
                Program.EC -> EC
                Program.BI -> BI
            }
    }
}
