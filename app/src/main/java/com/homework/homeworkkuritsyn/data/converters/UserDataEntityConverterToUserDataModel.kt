package com.homework.homeworkkuritsyn.data.converters

import com.homework.homeworkkuritsyn.data.network.models.UserDataModel
import com.homework.homeworkkuritsyn.domain.entity.UserDataEntity

data class UserDataEntityConverterToUserDataModel(val userDataEntity: UserDataEntity) {
}

fun UserDataEntityConverterToUserDataModel.asModel(): UserDataModel {
    return UserDataModel(
        firstName = userDataEntity.firstName,
        lastName = userDataEntity.lastName,
        phone = userDataEntity.phone,
        login = userDataEntity.login,
        password = userDataEntity.password
    )
}
data class UserDataModelConverterToEntity(val userDataModel: UserDataModel)
fun UserDataModelConverterToEntity.asEntity(): UserDataEntity {
    return UserDataEntity(
        firstName = userDataModel.firstName,
        lastName = userDataModel.lastName,
        phone = userDataModel.phone,
        login = userDataModel.login,
        password = userDataModel.password
    )
}
