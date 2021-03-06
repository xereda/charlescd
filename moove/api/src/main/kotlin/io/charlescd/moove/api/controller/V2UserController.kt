/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.charlescd.moove.api.controller

import io.charlescd.moove.application.ResourcePageResponse
import io.charlescd.moove.application.user.FindAllUsersInteractor
import io.charlescd.moove.application.user.FindUserByEmailInteractor
import io.charlescd.moove.application.user.response.UserResponse
import io.charlescd.moove.domain.PageRequest
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v2/users")
class V2UserController(
    private val findUserByEmailInteractor: FindUserByEmailInteractor,
    private val findAllUsersInteractor: FindAllUsersInteractor
) {

    @ApiOperation(value = "Find User by email")
    @GetMapping("/{email:.+}")
    @ResponseStatus(HttpStatus.OK)
    fun findByEmail(@PathVariable email: String): UserResponse {
        return findUserByEmailInteractor.execute(email)
    }

    @ApiOperation(value = "Find all Users")
    @GetMapping
    fun findAll(
        @RequestParam("name", required = false) name: String?,
        @RequestParam("email", required = false) email: String?,
        pageable: PageRequest
    ): ResourcePageResponse<UserResponse> {
        return this.findAllUsersInteractor.execute(name, email, pageable)
    }
}
