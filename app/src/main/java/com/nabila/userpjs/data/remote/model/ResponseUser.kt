package com.nabila.userpjs.data.remote.model

// generate by pojo generator

data class ResponseUser(
	val total: Int,
	val limit: Int,
	val skip: Int,
	val users: List<UsersItem>
)

data class Coordinates(
	val lng: Any,
	val lat: Any
)

data class Address(
	val country: String,
	val address: String,
	val city: String,
	val postalCode: String,
	val coordinates: Coordinates,
	val stateCode: String,
	val state: String
)

data class Company(
	val address: Address,
	val name: String,
	val department: String,
	val title: String
)

data class Hair(
	val color: String,
	val type: String
)

data class Crypto(
	val wallet: String,
	val coin: String,
	val network: String
)

data class UsersItem(
	val lastName: String,
	val role: String,
	val gender: String,
	val university: String,
	val maidenName: String,
	val ein: String,
	val ssn: String,
	val bloodGroup: String,
	val password: String,
	val hair: Hair,
	val bank: Bank,
	val eyeColor: String,
	val company: Company,
	val id: Int,
	val email: String,
	val height: Any,
	val image: String,
	val address: Address,
	val ip: String,
	val weight: Any,
	val userAgent: String,
	val birthDate: String,
	val crypto: Crypto,
	val firstName: String,
	val macAddress: String,
	val phone: String,
	val age: Int,
	val username: String
)

data class Bank(
	val iban: String,
	val cardExpire: String,
	val cardType: String,
	val currency: String,
	val cardNumber: String
)

