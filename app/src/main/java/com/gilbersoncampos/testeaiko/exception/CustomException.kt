package com.gilbersoncampos.testeaiko.exception

sealed class CustomException (message:String):Exception(message){
    data object NotFoundException:CustomException("Não encontrado")
    data object ErrorException:CustomException("Error")
}