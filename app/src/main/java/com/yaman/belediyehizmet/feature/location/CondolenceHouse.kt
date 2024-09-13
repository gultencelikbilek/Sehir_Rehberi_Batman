package com.yaman.belediyehizmet.feature.location


data class CondolenceHouse(
    val name: String = "",
    val adress: String = "",
    val latitude:  Double? = null,
    val longtude:  Double? = null,
    val image: String = ""
)


data class CondolenceListItem(
    val name: String? =null,
    val lat : Double? = null,
    val long : Double? = null,
    //   val location: LatLng? = null,
    val adress : String? = null

)

val condolenceHouseListLocation = listOf(
    CondolenceListItem(name = "Beşevler Taziye Evi", lat = 37.8877107, long = 41.1097408, adress = "Beşevler, 1321. Sk. No:28, 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "GAP Mahallesi Hacı Mehmet Şirin ÇELİK Taziye Evi",lat =37.9013586,41.1358857, adress = "Gap, 2618. Sk., 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Batman Çay Mahallesi Taziye Evi", 37.8863913,41.1024235, adress = "Çay, 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Huzur Taziye Evi", 37.8743562,41.1081001, adress = "Huzur, 521. Sk. No:16, 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Şafak Taziye Evi", 37.8891258,41.1389873, adress = "Şafak, 3405. Sk. No:23, 72070, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Sağlık Mahallesi Mehmet Şirin Öndaş Taziye Evi",37.8886707,41.1187345, adress = "Sağlık, 1909. Sk., 72060 Batman Merkez/Batman"),
    CondolenceListItem(name = "Yeșiltepe Taziye Evi", 37.8803632,41.1028846, adress = "Yeşiltepre, 2313. Sk. No:21, 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Şeyh Sait Taziye Evi", 37.8619436,41.1193957, adress = "Seyitler, 3008. Sk., 72040 Batman Merkez/Batman"),
    CondolenceListItem(name = "İluh Taziye Evi Mala Şiné", 37.8761185,41.1132707, adress = "İluh, Yaşam Cd., 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Eski Sanayi H. Halilaba Taziye Evi",37.8846321,41.1328437, adress = "Şirinevler, Toptancılar Cd. No:82, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Pınarbaşı Taziye Evi", 37.8953753,41.1248894, adress = "Pınarbaşı, 2213. Sk. No:38, 72060 Batman Merkez/Batman"),
    CondolenceListItem(name = "Çarsı Taziye Evi",   37.8769957,41.1176691, adress = "Çarşı, 726. Sk. No:6, 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Tilmerç Taziye Evi", 37.9189446,41.1496542, adress = "Unnamed Road, 72000, Demiryol/Batman Merkez/Batman"),
    CondolenceListItem(name = "Akyürek Taziye Evi", 37.879974,41.126586, adress = "Akyürek, 212. Çk. No:10, 72040 Batman Merkez/Batman"),
    CondolenceListItem(name = "Fekiye Teyra Taziye Evi", 37.8719316,41.1506171, adress = "Bayındır, Cizre Blv. No:47, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Bazıke Taziye Evi", 37.8695925,41.1131998, adress = "Hürriyet, 433. Sk. No:18, 72040 Batman Merkez/Batman"),
    CondolenceListItem(name = "Sofi Özdemir Taziye Evi", 37.8832213,41.143021, adress = "Yavuz Selim, Salih Özdemir Parkı Yanı,, Batman Merkez/Batman"),
    CondolenceListItem(name = "Cudi Mahallesi Taziye ve Halk Evi", 37.886119,41.1567794, adress = "Yavuz Selim, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "GÜNEYKENT TAZIYE EVI", 37.8673524,41.1060857, adress = "Güneykent, 72040 Batman Merkez/Batman"),
    CondolenceListItem(name = "Adile Kuba Taziye Evi",37.9223625,41.132439, adress = "Gültepe, Ömer Hayyam Cd., 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Petrolkent Mahallesi Heci Mustafa Görken Taziye Evi", 37.8589922,41.1270525, adress = "Seyitler, Çamlıbel Cd., 72040 Batman Merkez/Batman"),
    CondolenceListItem(name = "19 MAYIS TAZİYE EVİ",37.8830005,41.1107587, adress = "19 Mayıs, 923. Sk. No:9, 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Asr-ı Saadet Taziye Evi", 37.9104693,41.1433983, adress = "Belde, 3257. Sk, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "İpragaz Taziye Evi", 37.879886,41.156105, adress = "Petrol, 72040 Batman Merkez/Batman"),
    CondolenceListItem(name = "Cudi mahallesi MAVA TAZİYE EVİ",37.8869357,41.1514511, adress = "Yavuz Selim, 3509. Sk. No:102, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Adile Kubba Taziye Evi", 37.9241297,41.1341743, adress = "Gültepe, Sanayi Sitesi, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Şeyh Hüseyin Camii ve Taziye Evi", 37.8916887,41.1499901, adress = "Fatih, 3338. Sk. No:16, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Aydinlik Evler Mahallesi Taziye Evi",37.8855777,41.120151, adress = "Aydınlıkevler, Veysel Karani Cd., 72050 Batman Merkez/Batman"),
    CondolenceListItem(name = "Yazlica Seyfiye Köyü Taziye Evi", 37.8841938,41.1426687, adress = "Yavuz Selim, 3506. Sk. No:36, 72070 Batman Merkez/Batman"),
    CondolenceListItem(name = "Süleyman Nasiroğlu Taziye Evi", 37.9088606,41.2399292, adress = "Örmegöze Köyü Yolu, 72200 Örmegöze/Beşiri/Batman"),
)



val condolenceHouseList = listOf(
    CondolenceHouse(
        "Huzur Taziye  Evi",
        "Huzur, 521. Sk. No:16, 72050 Batman Merkez/Batman",
        37.8743562,
        41.1081001,
        "https://lh3.googleusercontent.com/p/AF1QipMVLBIaQ_s2T8430rqNHNl4SDKdT84HhRGXMn7U=s1360-w1360-h1020"
    )
    , CondolenceHouse(
        "Beşevler Taziye  Evi",
        "Beşevler, 1321. Sk. No:28, 72050 Batman Merkez/Batman",
        37.8877107,
        41.1097408,
        "https://lh3.googleusercontent.com/p/AF1QipPh8hip8J2wsTl3zXxT29CcHyr2kLe2DIGmwlFm=s1360-w1360-h1020"
    ), CondolenceHouse(
        "Batman Çay Mahallesi Taziye  Evi",
        "Çay, 72050 Batman Merkez/Batman",
        37.8863913,
        41.1024235,
        "https://lh3.googleusercontent.com/p/AF1QipNnRgTahCeDosHGCXh5pqfAFbVbkzIuxgtVq04s=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Yeșiltepe Taziye Evi",
        "Yeşiltepe, 2313. Sk. No:21, 72050 Batman Merkez/Batman",
        37.8803632,
        41.1028846,
        "https://lh3.googleusercontent.com/p/AF1QipOVM5zItSXryzRZIBiFR9sEnX074m6ACxigLir9=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "GAP Mahallesi Hacı Mehmet Şirin ÇELİK Taziye Evi",
        "Gap, 2618. Sk., 72070 Batman Merkez/Batman",
        37.9013586,
        41.1358857,
        "https://lh3.googleusercontent.com/p/AF1QipPWY5tEKJFYwW4NkjMiIua-1jrF_vpSNsYTDF-v=s294-w294-h220-n-k-no"
    ), CondolenceHouse(
        "Şafak Taziye Evi",
        "Şafak, 3405. Sk. No:23, 72070, 72070 Batman Merkez/Batman",
        37.8891258,
        41.1389873,
        "https://lh3.googleusercontent.com/p/AF1QipMqeL5jUWgqSshs7u0WAQrL181WhWYa08YRSo07=s294-w294-h220-n-k-no"
    ), CondolenceHouse(
        "Şeyh Sait Taziye Evi",
        "Seyitler, 3008. Sk., 72040 Batman Merkez/Batman",
        37.8619436,
        41.1193957,
        ""
    ), CondolenceHouse(
        "Eski Sanayi H. Halilaba Taziye Evi",
        "Şirinevler, Toptancılar Cd. No:82, 72070 Batman Merkez/Batman",
        37.8846321,
        41.1328437,
        ""
    ), CondolenceHouse(
        "İluh Taziye Evi",
        "İluh, Yaşam Cd., 72050 Batman Merkez/Batman",
        37.8761185,
        41.1132707,
        "https://lh3.googleusercontent.com/p/AF1QipNHpF-q1G5laVZKRnWDL4eRZSZAiK4Il-0OvN-i=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Çarsı Taziye Evi",
        "Çarşı, 726. Sk. No:6, 72050 Batman Merkez/Batman",
        37.8769957,
        41.1176691,
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=zXbKU-RLA3nS5d2AYq-OwA&cb_client=search.gws-prod.gps&yaw=40.475582&pitch=0&thumbfov=100&w=294&h=220"
    ), CondolenceHouse(
        "Tilmerç Taziye Evi",
        "Unnamed Road, 72000, Demiryol/Batman Merkez/Batman",
        37.9189446,
        41.1496542,
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=WP_znFArKz4FKoR-zpZY2A&cb_client=search.gws-prod.gps&yaw=93.22938&pitch=0&thumbfov=100&w=294&h=220"
    ), CondolenceHouse(
        "Pınarbaşı Taziye Evi",
        "Pınarbaşı, 2213. Sk. No:38, 72060 Batman Merkez/Batman",
        37.8953753,
        41.1248894,
        "https://lh3.googleusercontent.com/p/AF1QipNTQ21dA-rMiT_hYlaTIYaV3bboWkaMZ_PedsU0=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Akyürek  Taziye  Evi",
        "Akyürek, 212. Çk. No:10, 72040 Batman Merkez/Batman",
        37.879974,
        41.126586,
        "https://lh3.googleusercontent.com/p/AF1QipOnt64xIWglJCgheYnk4g_3Rf4l2vBIj5DZRGjp=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Sağlık mahallesi Mehmet Şirin Öndaş Taziye  Evi",
        "Sağlık, 1909. Sk., 72060 Batman Merkez/Batman",
        37.8886707,
        41.1187345,
        "https://lh3.googleusercontent.com/p/AF1QipNNkJpxw-rfD76pyrsFpDMhP-fk6Fv2Hi9RlhTE=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Bazıke Taziye  Evi",
        "Hürriyet, 433. Sk. No:18, 72040 Batman Merkez/Batman",
        37.8695925,
        41.1131998,
        "https://lh3.googleusercontent.com/p/AF1QipOvtKSHTQ4PM7Kr3olkxaQsJwxPWEGABYNxVXup=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Sofi Özdemir Taziye  Evi",
        "Yavuz Selim, Salih Özdemir Parkı Yanı,, Batman Merkez/Batman",
        37.8832213,
        41.143021,
        ""
    ), CondolenceHouse(
        "Fekiye Teyra Taziye  Evi",
        "Bayındır, Cizre Blv. No:47, 72070 Batman Merkez/Batman",
        37.8719316,
        41.1506171,
        "https://lh3.googleusercontent.com/p/AF1QipMuLctoFzx2E5_9ciDFBte2kQ5zlODW7UANl_hI=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Adile Kuba Taziye  Evi",
        "Gültepe, Ömer Hayyam Cd., 72070 Batman Merkez/Batman",
        37.9223625,
        41.132439,
        "https://lh3.googleusercontent.com/p/AF1QipPWhX6O9wOPBS8JKN4eEVAuv1aI-vef9Vl580bP=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Cudi Mahallesi Taziye ve Halk Evi",
        "Yavuz Selim, 72070 Batman Merkez/Batman",
        37.886119,
        41.1567794,
        "https://lh3.googleusercontent.com/p/AF1QipOYFvaNAeSY0bvm59n1GJNUGUQ0prsFbV_oGpuy=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "19 MAYIS Taziye  Evi",
        "19 Mayıs, 923. Sk. No:9, 72050 Batman Merkez/Batman",
        37.8830005,
        41.1107587,
        "https://lh3.googleusercontent.com/p/AF1QipODEN7WRwCOU4UNF0olsMFZcyWGfDYsSANpn7zC=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Petrolkent Mahallesi Heci Mustafa Görken Taziye Evi",
        "19 Mayıs, 923. Sk. No:9, 72050 Batman Merkez/Batman",
        37.8589922,
        41.1270525,
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=mtwu6S7FxLiLY99IDz3afw&cb_client=search.gws-prod.gps&yaw=5.191876&pitch=0&thumbfov=100&w=296&h=220"
    ), CondolenceHouse(
        "Asr-ı Saadet Taziye  Evi",
        "Belde, 3257. Sk, 72070 Batman Merkez/Batman",
        37.9104693,
        41.1433983,
        "https://lh3.googleusercontent.com/p/AF1QipM-BKSl8PMlZUr2CpxU4cXBz240EstvL5qt1NLG=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "GÜNEYKENT Taziye  Evi",
        "Güneykent, 72040 Batman Merkez/Batman",
        37.8673524,
        41.1060857,
        "https://lh3.googleusercontent.com/p/AF1QipN5xH2YKlmhmDwQo51KFMjFohtpCMqd_wSBp6RZ=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "İpragaz Taziye  Evi",
        "Petrol, 72040 Batman Merkez/Batman",
        37.879886,
        41.156105,
        ""
    ), CondolenceHouse(
        "Şeyh Hüseyin Camii ve Taziye  Evi",
        "Fatih, 3338. Sk. No:16, 72070 Batman Merkez/Batman",
        37.8916887,
        41.1499901,
        "https://lh3.googleusercontent.com/p/AF1QipMFmbXe-v1DHJLHZNz8QaOe1K0ITGD5eY35zpq5=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Cudi mahallesi MAVA Taziye  Evi",
        "Yavuz Selim, 3509. Sk. No:102, 72070 Batman Merkez/Batman",
        37.886119,
        41.1567794,
        "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=1ua4O1V7FG6c3fas45y-mQ&cb_client=search.gws-prod.gps&yaw=178.57727&pitch=0&thumbfov=100&w=296&h=220"
    ), CondolenceHouse(
        "Adile Kubba Taziye Evi",
        "Gültepe, Sanayi Sitesi, 72070 Batman Merkez/Batman",
        37.9241297,
        41.1341743,
        "https://lh3.googleusercontent.com/p/AF1QipOtldcq2VsVH6o_vad2EGreG-uF9AJgDq2b-_QS=s1360-w1360-h1020-rw"
    ), CondolenceHouse(
        "Aydinlik Evler Mahallesi  Taziye Evi",
        "Aydınlıkevler, Veysel Karani Cd., 72050 Batman Merkez/Batman",
        37.8855777,
        41.120151,
        "https://lh3.googleusercontent.com/p/AF1QipNE4te_zVS8VZnjKyEH2t6Cvzn82W-ma3r8IRLh=s1360-w1360-h1020-rw"
    )
)