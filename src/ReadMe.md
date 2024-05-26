Java Spring Boot - Veteriner Yönetim Sistemi 

Veteriner yönetim sistemi, bir veteriner kliniğinin operasyonlarını yönetmesi için bir API sağlar.
Bu proje veteriner personeli tarafından kullanılmak üzere tasarlanmıştır.

Uyulamayı çalıştırmak için: http://localhost:8047

Veteriner yönetim sistemi API'si  temel özellikleri:

Hayvanların ve Sahiplerinin (customer) Yönetimi

* Hayvanları kaydetme, bilgilerini güncelleme, görüntüleme ve silme
* Hayvan sahiplerini kaydetme, bilgilerini güncelleme, görüntüleme ve silme
* Hayvan sahiplerinin ismine göre filtreleme ve hayvan isimlerine göre filtreleme 
* Hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleme. Hayvan sahibine göre hayvanlara filtreleme yapma.

Uygulanan Aşıların Yönetimi

* Hayvanlara uygulanan aşıları kaydetme, bilgilerini güncelleme, görüntüleme ve silme
* Bir hayvanın kimliğine göre aşıların listelenmesi
* Aşı korumasının bitiş tarihine göre hayvanların listelenmesi


Randevu Yönetimi

* randevularının oluşturulması, bilgilerinin güncellenmesi, görüntülenmesi ve silinmesi
* Randevuları belirli bir tarih aralığına ve doktora göre filtreleme
* Randevuları belirli bir tarih aralığına ve hayvana göre filtreleme

Doktor Yönetimi

* Veteriner ekleme, bilgilerinin güncellenmesi, görüntülenmesi ve silinmesi
* Doktor müsait tarihlerinin yönetimi
* Doktor için uygun tarihleri ekleme , güncelleme ve silme

API end pointleri

**-VACCİNE-**

* Aşı bilgilerini günceller.
 `PUT /v1/vaccines/update/{id}`
* Yeni aşı kaydı yapar.
 `POST /v1/vaccines/createdNew`
* Id'ye göre aşıyı getirir.
 `GET /v1/vaccines/{id}`
* Id'ye göre aşıyı siler
 `DELETE /v1/vaccines/{id}`
* Belirli bir tarih aralığındaki aşıları getirir.
  `GET /v1/vaccines/date-range`
* Hayvan ID'sine göre aşıları getirir.
  `GET /v1/vaccines/animal/{animalId}`
* Hayvan ID'sine ve tarih aralığına göre aşıları getirir.
  `GET /v1/vaccines/animal/filter/date`
* Sistemdeki tüm Aşıları getirir.
  `GET /v1/vaccines/all `


**-DOCTOR-**

* Doktor bilgilerini günceller.
  `PUT /v1/doctors/update/{id}`
* Yeni doktor kaydı yapar.
  `POST /v1/doctors/createdNew`
* Id'ye göre doktoru getirir.
  `GET /v1/doctors/{id}`
* Id'ye göre doktoru siler.
  `DELETE /v1/doctors/{id}`
* Sistemdeki tüm doktorları getirir.
  `GET /v1/doctors/all`


**-ANIMAL-**

* Hayvan bilgilerini günceller.
  `PUT /v1/animals/update/{id}`
* Yeni hayvan kaydı yapar.
  `POST /v1/animals/createdNew`
* Id'ye göre hayvanı getirir.
  `GET /v1/animals/{id}`
* Id'ye göre hayvanı siler.
  `DELETE /v1/animals/{id}`
* Bir hayvana ait aşıları listeler.
  `GET /v1/animals/{id}/vaccines`
* Hayvanın ismine göre getirir
  `GET /v1/animals/filter`
* Hayvan sahibinin ID'sine göre hayvanları getirir
  `GET /v1/animals/customer/{customerId}`
* Sistemdeki tüm Hayvanları getirir.
  `GET /v1/animals/all`

**-AVAILABLE-DATE-**

* Müsait gün kaydı yapar
  `POST /v1/available-dates/createNew`
* Id'ye göre müsait günü getirir.
  `GET  /v1/available-dates/{id}`
* Id'ye göre müsait günü siler.
  `DELETE /v1/available-dates/{id}`
* Sistemdeki tüm Müsait günleri getirir
  `GET /v1/available-dates/all`  

**-APPOINTMENT-**

* Randevu kaydı yapar
`POST /v1/appointments/createdNew`
* Id'ye göre randevuyu getirir
`GET /v1/appointments/{id}`
* Id'ye göre randevuyu siler
`DELETE /v1/appointments/{id}`
* Doktor ID'sine ve tarih aralığına göre randevuları filtreler
`GET /v1/appointments/filter/doctor/{doctorId}`
* Belirli bir tarih aralığına göre randevuları filtreler
`GET /v1/appointments/filter/date`
* Animal ID'sine ve tarih aralığına göre randevuları filtreler
`GET /v1/appointments/filter/animal/{animalId}`
* Sistemdeki tüm randevuları getirir.
`GET /v1/appointments/all `


