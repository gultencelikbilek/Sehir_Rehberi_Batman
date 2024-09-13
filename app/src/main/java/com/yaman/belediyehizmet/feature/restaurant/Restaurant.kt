package com.yaman.belediyehizmet.feature.restaurant

data class Restaurant(
    val image : String,
    val name : String,
    val latitude:  Double? = null,
    val longtude:  Double? = null,
)


val restaurants = listOf(
    Restaurant("https://lh5.googleusercontent.com/p/AF1QipOoHVBPbpCvD9sTKN0K1HSS7dVr5rtzCH4BHON-=w243-h174-n-k-no-nu","Çömçe Et Lokantası", latitude = 37.8888583, longtude =41.1292272 ),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipOTIVTodZW32odT_pOq_4ajcyWL5FBNmGEupgu4=s1360-w1360-h1020-rw","Ziyade Et Lokantası", latitude = 37.908954, longtude = 41.137144),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipM1BukinJ7hiDU3vHekCauHyvSRQ3q1Y2aUycNe=s1360-w1360-h1020-rw","Kaşık Restoran", latitude = 37.9193508, longtude = 41.1296303),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipPvAVIJj54BqktX6WjI8w61tYMfs9PJbXhPcyR3=s1360-w1360-h1020-rw","Ali USTA Et Lokantası", latitude = 37.9034464, longtude =41.1305956 ),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipMg4HERznHP8NaW9HCMRYeX-CLSUATOMequrIAP=s1360-w1360-h1020-rw","Diyar Et Lokantası", latitude = 37.8903888, longtude = 41.1262641),
    Restaurant("","Meshur Ciğerci Çavuş Usta'nın Yeri", latitude = 37.9027186, longtude = 41.1018784),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipOIRy7GytQbJV0KooWfNx4QQmcx287jZiMYgHP2=s1360-w1360-h1020-rw","Xalo Hâyran Ciğer Salonu", latitude = 37.8808983, longtude =41.1190777 ),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipOwdCKE-KyPsPj-C6bMHOjgZx6RKczALqxORJJo=s1360-w1360-h1020-rw","Petrol Cafe", latitude =37.8720925 , longtude = 41.1303612),
    Restaurant("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/26/7f/e4/batman-da-kebap-icin.jpg?w=600&h=400&s=1","Adana Kebap Bahçesi - Abdullah Usta", latitude = 37.8782237, longtude = 41.1657073),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipNOF7oC8TrpCJXH3FqH0a0pgzGIGhuQGDqlm1VU=s1360-w1360-h1020-rw","Balık Evi & Izgara", latitude =37.9024549 , longtude = 41.1228293),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipOa8qObv-qxmDKABqoGhxiw2AW1nbCXVtNSqQFw=s1360-w1360-h1020-rw","ŞİRİN OCAKBAŞI RESTORAN LOKANTA", latitude = 37.8893998, longtude =41.151938),
    Restaurant("https://lh5.googleusercontent.com/p/AF1QipMSDMpagN0Ia_hAB9BKDCalD3MXdbYOm6lLUHgR=w243-h406-n-k-no-nu","QuuBa Restaurant", latitude = 37.9184233, longtude =41.1396127),
    Restaurant("https://lh3.googleusercontent.com/p/AF1QipNtBOpjYTXePQbhJd-3UnK0QE1e_aDjqNZhmxu0=s1360-w1360-h1020-rw","Geçit Büryan", latitude = 37.8802862, longtude =41.0897795),
    Restaurant("https://lh5.googleusercontent.com/p/AF1QipOhOr61IoG8CoSedcLf7xGmebyXG6Fyjeorot86=w1920-h1080-n-k-no","World Cafe Restaurant", latitude = 37.8957441, longtude =41.1271355),
    Restaurant("","Saray Et Lokantası")
)
