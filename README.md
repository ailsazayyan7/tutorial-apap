# Tutorial APAP

## Authors
* **ailsa.zayyan** - *1706074682* - *APAP-C*
---
## Tutorial 1
### What I have learned today
#### Github
1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?
Issue tracker merupakan sebuah wadah untuk menyampaikan ide, mengumpulkan atau menyampaikan tugas, atau pun mencatat bugs yang ada di suatu fitur yang dibuat di github. Issue tracker juga bisa digunakan sebagai wadah untuk mengumpulkan user feedback dan melaporkan software bugs 
2. Apa perbedaan dari git merge dan merge --squash?
ketika kita git merge beberapa fitur yang telah di commit sebelumnya, fitur tersebut akan dimerge 1 per 1, sehingga di master akan terlihat 1 per 1 hasil commit masing-masing fitur. Sedangkan, jika kita melakukan merge --squash, beberapa fitur yang telah dicommit sebelumnya akan di merge menjadi 1 bagian yang sama
#### Spring
3. Apa itu library & dependency?
Library merupakan modul-modul yang sudah disediakan oleh Spring dan hanya perlu diimport apabila ingin digunakan. Dependency digunakan untuk mempermudah pengguna agar dapat menentukan kebutuhan dari suatu program sesuai dari dependency program tersebut
4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?
Maven merupakan Java build automation tool yang utamanya digunakan untuk java project. Maven membantu kita untuk lebih mudah mengompilasi source code, melakukan testing, dan menginstall library yang dibutuhkan
5. Apa alternatif dari Maven?
Gradle, Jenkins, Vortex
### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda sudah mengerti
dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP?
- [ ] Apa itu Spring?
- [ ] Memahami message commit yang baik
- [ ] Penggunaan fitur-fitur github secara lancar
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu lebih dalam
tentang penulisan README.md pada github pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))


## Tutorial 2
Pertanyaan 1: Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
- Ketika pertama kali membuka link, link tersebut tidak dapat diakses karena saya belum membuat view add-restoran, sedangkan saya telah mengetikkan perintah "return add-restoran"

Pertanyaan 2: Cobalah untuk menambahkan sebuah restoran dengan mengakses link
berikut:
http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
- Terjadi error ketika membuka link tersebut karena nomor telepon tidak didefinisikan di link. Padahal nomor telepon adalah constructor dari objek restoran.

Pertanyaan 3: Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus diakses?
- http://localhost:8080/restoran/view?idRestoran=1

Pertanyaan 4: Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall, apa yang akan ditampilkan? 
Sertakan juga bukti screenshotmu.
- ![bukti](src/main/resource/static/ssaddresto.jpg


LATIHAN
1. Pada RestoranController tambahkan sebuah method view Restoran dengan menggunakan
Path Variable. Misalnya, kamu ingin memasukkan data sebuah Restoran yang memiliki
idRestoran 1, untuk melihat data yang baru dimasukkan tersebut, user dapat mengakses
halaman localhost:8080/restoran/view/id-restoran/1.

Saya menambahkan code berikut dibagian RestoranController
    // URL mapping id-restoran
    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
    public String viewId(@PathVariable("idRestoran") String idRestoran, Model model){
    	// Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        // Return view template
        return "view-restoran";
    }


2. Tambahkan fitur untuk melakukan update nomorTelepon Restoran berdasarkan idRestoran.
Misalnya, setelah melakukan add Restoran pada soal nomor 1 bab View Template, cobalah
untuk mengubah nomorTelepon objek Restoran tersebut menjadi 911 dengan mengakses
halaman localhost:8080/restoran/update/id-restoran/1/nomor-telepon/911.
Tampilkan juga sebuah halaman yang memberikan informasi bahwa data tersebut telah
berhasil diubah.

menambahkan fungsi update di RestoranController yang mengambil idRestoran dan nomorTelepon dari link untuk ditampilkan dan diubah nomorTeleponnya.
Kemudian buat file update-telepon.html untuk memberi informasi bahwa data telah diubah

3. Tambahkan fitur untuk melakukan delete Restoran berdasarkan idRestoran. Misalnya,
setelah melakukan add Restoran pada soal nomor 1 bab View Template dan melakukan update
seperti pada soal nomor 2 bab Latihan, cobalah untuk melakukan delete data tersebut dengan
mengakses halaman localhost:8080/restoran/delete/id/1. Tampilkan sebuah halaman yang
memberikan informasi bahwa data tersebut telah berhasil dihapus.
Notes: Jika idRestoran tidak diberikan atau tidak ditemukan kembalikan halaman error yang
berisi informasi bahwa idRestoran kosong atau tidak ditemukan dan proses
view/update/delete dibatalkan.

tambahkan method delete di interface RestoranService dan implements di RestoranInMemoryService.
buat method baru lagi di RestoranController dan buat 2 kondisi, id ditemukan atau id tidak ditemukan.
jika ditemukan, lakukan fungsi delete yang telah dibuat di RestoranService.

buat 2 file html, 1 untuk halaman berhasil, dan 1nya untuk halaman error
=======
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))


## Tutorial 3
### gopud

#### PERTANYAAN
1. Pada class MenuDb, terdapat method findByRestoranIdRestoran, apakah kegunaan dari
method tersebut?
Untuk mencari semua menu yang dimiliki oleh restoran berdasarkan id nya. Nantinya akan digunakan di MenuServiceImpl.java
method tersebut memiliki parameter idRestoran yang ingin dicari, kemudian me return List Menu yang dimiliki restoran.
2. Pada class RestoranController, jelaskan perbedaan method addRestoranFormPage dan
addRestoranSubmit?
addRestoranFormPage merupakan method untuk menampilkan form menambah restoran. method tersebut me return html page
"form-add-restoran", sedangkan addRestoranSubmit merupakan method yang akan dijalankan ketika tombol submit diklik sehingga
method tersebut akan menambahkan data-data restoran yang telah diinput pada form sebelumnya melalui method addRestoran
yang dimiliki oleh restoranService. Selain itu, method addRestoranSubmit akan mengembalikan html page add-restoran.html
3. Jelaskan apa kegunaan dari JPA Repository?
JPA Repository digunakan untuk memasukkan objek-objek Java ke dalam sebuah relational
database. Kemudian JPA Repo juga dapat digunakan untuk mengatur database tanpa menggunakan query
4. Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara RestoranModel dan
MenuModel dibuat?
terdapat @OneToMany dan @ManyToOne yang menunjukkan relasi model-model tersebut.
@OneToMany yang ada di RestoranModel menunjukkan bahwa satu restoran dapat memiliki banyak menu, dan
@ManyToOne yang ada di MenuModel menunjukkan bahwa banyak menu dapat dimiliki satu restoran.
5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
FetchType.LAZY digunakan saat kita hanya ingin melakukan fetch saat kita membutuhkan datanya. Biasanya, FetchType
ini digunakan untuk relasi one-to-many atau many-to-many.

Sebaliknya, FetchType.EAGER digunakan saat kita ingin
data yang akan di-*fetch* sudah ada saat kita membutuhkannya, jadi ia melakukan fetch seawal mungkin. Biasanya,
FetchType ini digunakan untuk relasi many-to-one atau one-to-one.

CascadeType.ALL digunakan agar segala perubahan yang terjadi pada suatu entitas akan terjadi juga pada entitas
yang memiliki relasi ini dengannya. Perubahan dapat terjadi karena DELETE, UPDATE, dan sebagainya. Sebagai contoh,
ketika kita menghapus suatu restoran, maka semua menu yang terkait dengan restoran tersebut juga dapat dihapus.


## Tutorial 4
###PERTANYAAN
1. Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana
anda menyelesaikan latihan nomor 2
- Pada latihan nomor 2, saya diharuskan untuk mengubah title pada navbar yang aktif. Pertama saya menambahkan variable di fragment.html. variable tersebut saya beri nama pageTitle. Variable tersebut akan berubah tergantung dengan pageTitle yang dimiliki oleh setiap halaman html yang ada di gopud.
Misalkan pada halaman home, saya memberikan nilai variabel pageTitle dengan nama "Home". Nantinya variabel pageTitle yang dituliskan pada fragment.html akan digantikan dengan tulisan 'Home'
2. Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda
menyelesaikan latihan nomor 2
-
3. Jelaskan perbedaan th:include dan th:replace
- jika kita menggunakan th:include, fragment akan diletakkan didalam tag yg mengantung th:include tersebut.
Sedangkan apabila menggunakan th:replace, seluruh isi tag akan digantikan dengan fragment. 
4. Jelaskan bagaimana penggunaan th:object beserta tujuannya
th:object berfungsi untuk menjadikan atributnya sebagai object dari operasi-operasi dibawahnya.
Misalkan kita menuliskan th:object="${restoran}", maka restoran dapat digunakan sebagai object pada operasi selanjutnya hanya dengan menuliskan tanda bintang.
<p>< span th :text="* {nama}" >kaefci< /span></p>
<p>< span th :text=" $ {restoran.nama}"> mekdi< /span>.</p>

##Tutorial 5
###PERTANYAAN
1. **Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and
then:**
*Given* pada unit test tersebut adalah RestoranModel restoran = generateDummyRestoranModel(1); karena part dari test tersebut merupakan kondisi-kondisi awal inisiasi Test. *given* tersebut menginisiasi RestoranModel menggunakan method generateDummyRestoran.  when(restoranService.getRestoranByIdRestoran(1L)).thenReturn(Optional.of(restoran)); juga merupakan bagian dari given karena membantu proses inisiasi (apa yang harus dilakukan ketika terjadi interaksi tertentu)
<br>bagian *When* pada unit test yang dibuat adalah bagian code wherestoranService.getRestoranByIdRestoran(1L), karena bagian itulah yang ingin kita test
<br>Sisa dari code test tersebut merupakan bagian dari *And Then*, karena menampilkan reaksi apa yang kita harapkan. andExpect dan verify merupakan bagian dari andThen 

2. **Jelaskan perbedaan line coverage dan logic coverage:**
Line coverage menghitung persentase line yang dieksekusi oleh unit test. Logic coverage sendiri merupakan 
jenis testing yang dilakukan untuk masing-masing bagian tertentu, contohnya kondisi if else yang ada di dalam suatu use case.
3. **Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, code atau unit test? Mengapa
seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin
terjadi?**
<br> Seharusnya, yang harus dibuat adalah unit test. Unit Test berfungsi untuk menentukan dari awal standar-standar apa yang seharusnya diterapkan oleh suatu aplikasi. Pada Unit Test, kita juga dapat mengontrol program dengan baik. Selain itu, dengan adanya unit test, proses development akan lebih cepat karena kita sudah menentukan standar-standarnya pada unit test.<br>
Jika kita membuat code lebih dulu dibandingkan unit test, tentu proses development akan lebih lama. Selain itu, kita mungkin kesulitan untuk mencari solusi error yang muncul.

4. **[Bonus] Jelaskan mengapa pada latihan no 3, main class spring tidak diikutsertakan ke dalam
perhitungan coverage? Apa saja yang dapat menyebabkan suatu class dapat di-exclude dari
perhitungan code coverage.**
<br>Karena main class pada project gopud ini hanya bersifat konfigurasi dari project, tidak perlu disertakan pada test coverage. Jika tetap disertakan, perhitungan coverage akan tetap mengitung code main class tersebut. Dengan
mengexclude main class, coverage akan lebih dapat terlihat secara detail.


##Screen Capture Coverage
###MenuServiceTest
######Before
![before](src/main/resource/static/menuService_before.jpg)
######After
![after](src/main/resource/static/menuService_after.jpg)

###RestoranControllerTest
![before](src/main/resource/static/restoranController_before.jpg)
######After
![after](src/main/resource/static/restoranController_after.jpg)

##Tutorial 7
1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode
   yang telah anda buat) konsep tersebut diimplementasi?
   - Authentication : Memastikan bahwa pengguna merupakan user yang sesuai. implementasinya terdapat pada form login dimana kita memastikan apakah pengguna memasukan username serta password yang tepat atau tidak
   - Authorization : Menentukan apakah user memiliki akses terhadap suatu fitur atau tidak. implementasi kode terdapat pada method configure yang berada di WebSecurityConfig
2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerjanya!
  -BCryptPasswordEncoder adalah salah satu tool yang digunakan untuk melakukan encode terhadap
   password yang ada di Spring. Jadi untuk kepentingan Security,
   suatu password tidak boleh disimpan dalam bentuk plain. Sehingga
   perlu di encode dengan memanfaatkan hashing. 
   Cara penggunaannya adalah dengan membuat objek BCryptPasswordEncoder, lalu objek
   tersebut dipanggil dan menggunakan method encode untuk
   melakukan encode terhadap password tersebut 
3. Jelaskan secara singkat apa itu UUID dan mengapa kita memakai UUID di UserModel.java?
    - UUID adalah kumpulan 32 karakter (String) yang dibuat secara random dengan teknik khusus yang dijamin unik untuk setiap data. Dalam waktu 1 detik pun, jika di-generate 1000 UUID, 
    kecil kemungkinan ada UUID yang sama. Sehingga lebih cocok untuk digunakan sebagai Primary Key. Sehingga pada UserModel.java memakai UUID untuk keunikan antar user dan juga keamanan.
    UUID tersebut tentu saja sulit ditebak oleh pengguna karena tidak mempunyai pola khusus. Jika ada hacker yang ingin menggunakan program looping untuk mendapatkan seluruh data User, maka dia perlu membuat banyak kombinasi 32 karakter tersebut, tentu tidak mudah dan membutuhkan waktu lama.

4. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut
   padahal kita sudah memiliki class UserRoleServiceImpl.java?
   - UserDetailsServiceImpl.java berguna untuk membangun autentikasi user. didalam class ini dilakukan extends terhadap UserServiceDetail dari spring security.
     Class ini akan menghasilkan UserDetail object yang akan memberikan informasi terkait user yang terdapat dari db dan juga memberikan otorisasi
     yang dimiliki user sesuai dengan role yang dimiliki. Sedangkan UserServiceImpl digunakan untuk mengatur mapping yang dilakukan oleh user