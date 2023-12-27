package com.amira.bostaapp.data.local

import androidx.room.TypeConverter
import com.amira.bostaapp.domain.model.Address
import com.amira.bostaapp.domain.model.Company
import com.amira.bostaapp.domain.model.Geometric

import com.google.gson.Gson


class Converters {
    @TypeConverter
    fun fromCompanyToGson(company: Company?): String {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun fromGsonToCompany(stringCompany: String?): Company {
        return Gson().fromJson(stringCompany, Company::class.java)
    }

    @TypeConverter
    fun fromAddressToGson(address: Address?): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun fromGsonToAddress(stringAddress: String?): Address {
        return Gson().fromJson(stringAddress, Address::class.java)
    }

    @TypeConverter
    fun fromGeoToGson(geometric: Geometric?): String {
        return Gson().toJson(geometric)
    }

    @TypeConverter
    fun fromGsonToGeo(stringGeometric: String?): Geometric {
        return Gson().fromJson(stringGeometric, Geometric::class.java)
    }
}