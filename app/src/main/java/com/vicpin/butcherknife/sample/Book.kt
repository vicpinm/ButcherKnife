package com.vicpin.butcherknife.sample


import com.vicpin.butcherknife.annotation.*

/**
 * Created by Oesia on 07/12/2017.
 */

class Book(@BindDate(id = R.id.text, format = R.string.date)
           val date: Long,
           @BindText(id = R.id.url)
           val url: Int,
           @BindImage(id = R.id.img, placeholder = R.mipmap.ic_launcher, cornerRadius = R.dimen.corners, cornerType = CornerType.ALL)
           val image: String,
           @BindBool(id = R.id.cd)
           val myboolean: Boolean,
           @BindBool(id = R.id.witch)
           val myboolean2: Boolean,
           @BindBool(id = R.id.db)
           val myboolean3: Boolean

)
