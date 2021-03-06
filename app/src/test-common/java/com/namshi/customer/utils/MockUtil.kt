/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.namshi.customer.utils

import com.namshi.customer.model.Image
import com.namshi.customer.model.NamshiResponses
import com.namshi.customer.model.NamshiWidget

object MockUtil {

    fun mockContent() = NamshiResponses.Content(
        type = NamshiWidget.Type.image,
        cols = 1,
        images = mockContentImageList()
    )

    fun mockContentList() = mutableListOf(mockContent())

    fun mockContentImage() = Image(
        url = "https://a.namshicdn.com/cms/small/search/20180219/generic/module_01_en.jpg",
        width = 640,
        height = 152,
        format = Image.Format.image
    )

    fun mockContentImageList() = mutableListOf(mockContentImage())

}
