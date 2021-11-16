package com.example.retrofit_practice.di.modules

import com.example.retrofit_practice.R.string.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CountryNamesModule {
    @Provides
    @Named("countryList")
    fun provideCountriesStringIDList() =
        arrayListOf<Int>(
            Afghanistan,
            Albania,
            Algeria,
            Andorra,
            Angola,
            AntiguaBarbuda,
            Argentina,
            Armenia,
            Australia,
            Austria,
            Azerbaijan,
            Bahamas,
            Bahrain,
            Bangladesh,
            Barbados,
            Belarus,
            Belgium,
            Belize,
            Benin,
            Bhutan,
            Bolivia,
            BosniaHerzegovina,
            Botswana,
            Brazil,
            Brunei,
            Bulgaria,
            BurkinaFaso,
            Burma,
            Burundi,
            CaboVerde,
            Cambodia,
            Cameroon,
            Canada,
            CentralAfricanRepublic,
            Chad,
            Chile,
            China,
            Colombia,
            Comoros,
            CongoBrazzaville,
            CongoKinshasa,
            CostaRica,
            Croatia,
            Cuba,
            Cyprus,
            Czechia,
            Denmark,
            Djibouti,
            Dominica,
            DominicanRepublic,
            Ecuador,
            Egypt,
            ElSalvador,
            EquatorialGuinea,
            Eritrea,
            Estonia,
            Eswatini,
            Ethiopia,
            Fiji,
            Finland,
            France,
            Gabon,
            Gambia,
            Georgia,
            Germany,
            Ghana,
            Greece,
            Grenada,
            Guatemala,
            Guinea,
            GuineaBissau,
            Guyana,
            Haiti,
            HolySee,
            Honduras,
            Hungary,
            Iceland,
            India,
            Indonesia,
            Iran,
            Iraq,
            Ireland,
            Israel,
            Italy,
            Jamaica,
            Japan,
            Jordan,
            Kazakhstan,
            Kenya,
            Kiribati,
            KoreaSouth,
            Kosovo,
            Kuwait,
            Kyrgyzstan,
            Laos,
            Latvia,
            Lebanon,
            Lesotho,
            Liberia,
            Libya,
            Liechtenstein,
            Lithuania,
            Luxembourg,
            Madagascar,
            Malawi,
            Malaysia,
            Maldives,
            Mali,
            Malta,
            MarshallIslands,
            Mauritania,
            Mauritius,
            Mexico,
            Micronesia,
            Moldova,
            Monaco,
            Mongolia,
            Montenegro,
            Morocco,
            Mozambique,
            Namibia,
            Nepal,
            Netherlands,
            NewZealand,
            Nicaragua,
            Niger,
            Nigeria,
            NorthMacedonia,
            Norway,
            Oman,
            Pakistan,
            Palau,
            Panama,
            PapuaNewGuinea,
            Paraguay,
            Peru,
            Philippines,
            Poland,
            Portugal,
            Qatar,
            Romania,
            Russia,
            Rwanda,
            SaintKittsAndNevis,
            SaintLucia,
            SaintVincentAndTheGrenadines,
            Samoa,
            SanMarino,
            SaoTomeAndPrincipe,
            SaudiArabia,
            Senegal,
            Serbia,
            Seychelles,
            SierraLeone,
            Singapore,
            Slovakia,
            Slovenia,
            SolomonIslands,
            Somalia,
            SouthAfrica,
            SouthSudan,
            Spain,
            SriLanka,
            Sudan,
            Suriname,
            Sweden,
            Switzerland,
            Syria,
            Tajikistan,
            Tanzania,
            Thailand,
            TimorLeste,
            Togo,
            Tonga,
            TrinidadAndTobago,
            Tunisia,
            Turkey,
            Turkmenistan,
            Tuvalu,
            Uganda,
            Ukraine,
            UnitedArabEmirates,
            UnitedKingdom,
            UnitedStatesOfAmerica,
            Uruguay,
            Uzbekistan,
            Vanuatu,
            Venezuela,
            Vietnam,
            WestBankGaza,
            Yemen,
            Zambia,
            Zimbabwe
        )
}