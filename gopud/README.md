Tutorial APAP
Authors<br>
**ailsa.zayyan** - **1706074682** - APAP-C<br>
#Tutorial 1
What I have learned today
Github
Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker? Issue tracker merupakan sebuah wadah untuk menyampaikan ide, mengumpulkan atau menyampaikan tugas, atau pun mencatat bugs yang ada di suatu fitur yang dibuat di github. Issue tracker juga bisa digunakan sebagai wadah untuk mengumpulkan user feedback dan melaporkan software bugs
Apa perbedaan dari git merge dan merge --squash? ketika kita git merge beberapa fitur yang telah di commit sebelumnya, fitur tersebut akan dimerge 1 per 1, sehingga di master akan terlihat 1 per 1 hasil commit masing-masing fitur. Sedangkan, jika kita melakukan merge --squash, beberapa fitur yang telah dicommit sebelumnya akan di merge menjadi 1 bagian yang sama
Spring
Apa itu library & dependency? Library merupakan modul-modul yang sudah disediakan oleh Spring dan hanya perlu diimport apabila ingin digunakan. Dependency digunakan untuk mempermudah pengguna agar dapat menentukan kebutuhan dari suatu program sesuai dari dependency program tersebut
Apa itu Maven? Mengapa kita perlu menggunakan Maven? Maven merupakan Java build automation tool yang utamanya digunakan untuk java project. Maven membantu kita untuk lebih mudah mengompilasi source code, melakukan testing, dan menginstall library yang dibutuhkan
Apa alternatif dari Maven? Gradle, Jenkins, Vortex
What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-check apabila Anda sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)

 Kenapa saya harus belajar APAP?
 Apa itu Spring?
 Memahami message commit yang baik
 Penggunaan fitur-fitur github secara lancar (Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu lebih dalam tentang penulisan README.md pada github pada link berikut)
Tutorial 2
Pertanyaan 1: Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut: http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022 Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Ketika pertama kali membuka link, link tersebut tidak dapat diakses karena saya belum membuat view add-restoran, sedangkan saya telah mengetikkan perintah "return add-restoran"
Pertanyaan 2: Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut: http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

Terjadi error ketika membuka link tersebut karena nomor telepon tidak didefinisikan di link. Padahal nomor telepon adalah constructor dari objek restoran.
Pertanyaan 3: Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus diakses?

http://localhost:8080/restoran/view?idRestoran=1
Pertanyaan 4: Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

![bukti](src/main/resource/static/ssaddresto.jpg
LATIHAN

Pada RestoranController tambahkan sebuah method view Restoran dengan menggunakan Path Variable. Misalnya, kamu ingin memasukkan data sebuah Restoran yang memiliki idRestoran 1, untuk melihat data yang baru dimasukkan tersebut, user dapat mengakses halaman localhost:8080/restoran/view/id-restoran/1.
Saya menambahkan code berikut dibagian RestoranController // URL mapping id-restoran @RequestMapping("/restoran/view/id-restoran/{idRestoran}") public String viewId(@PathVariable("idRestoran") String idRestoran, Model model){ // Mengambil objek RestoranModel yang dituju RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

    // Add model restoran ke "resto" untuk dirender
    model.addAttribute("resto", restoran);

    // Return view template
    return "view-restoran";
}
Tambahkan fitur untuk melakukan update nomorTelepon Restoran berdasarkan idRestoran. Misalnya, setelah melakukan add Restoran pada soal nomor 1 bab View Template, cobalah untuk mengubah nomorTelepon objek Restoran tersebut menjadi 911 dengan mengakses halaman localhost:8080/restoran/update/id-restoran/1/nomor-telepon/911. Tampilkan juga sebuah halaman yang memberikan informasi bahwa data tersebut telah berhasil diubah.
menambahkan fungsi update di RestoranController yang mengambil idRestoran dan nomorTelepon dari link untuk ditampilkan dan diubah nomorTeleponnya. Kemudian buat file update-telepon.html untuk memberi informasi bahwa data telah diubah

Tambahkan fitur untuk melakukan delete Restoran berdasarkan idRestoran. Misalnya, setelah melakukan add Restoran pada soal nomor 1 bab View Template dan melakukan update seperti pada soal nomor 2 bab Latihan, cobalah untuk melakukan delete data tersebut dengan mengakses halaman localhost:8080/restoran/delete/id/1. Tampilkan sebuah halaman yang memberikan informasi bahwa data tersebut telah berhasil dihapus. Notes: Jika idRestoran tidak diberikan atau tidak ditemukan kembalikan halaman error yang berisi informasi bahwa idRestoran kosong atau tidak ditemukan dan proses view/update/delete dibatalkan.
tambahkan method delete di interface RestoranService dan implements di RestoranInMemoryService. buat method baru lagi di RestoranController dan buat 2 kondisi, id ditemukan atau id tidak ditemukan. jika ditemukan, lakukan fungsi delete yang telah dibuat di RestoranService.

buat 2 file html, 1 untuk halaman berhasil, dan 1nya untuk halaman error
berikut)

Tutorial 3
gopud
PERTANYAAN
Pada class MenuDb, terdapat method findByRestoranIdRestoran, apakah kegunaan dari method tersebut? Untuk mencari semua menu yang dimiliki oleh restoran berdasarkan id nya. Nantinya akan digunakan di MenuServiceImpl.java method tersebut memiliki parameter idRestoran yang ingin dicari, kemudian me return List Menu yang dimiliki restoran.
Pada class RestoranController, jelaskan perbedaan method addRestoranFormPage dan addRestoranSubmit? addRestoranFormPage merupakan method untuk menampilkan form menambah restoran. method tersebut me return html page "form-add-restoran", sedangkan addRestoranSubmit merupakan method yang akan dijalankan ketika tombol submit diklik sehingga method tersebut akan menambahkan data-data restoran yang telah diinput pada form sebelumnya melalui method addRestoran yang dimiliki oleh restoranService. Selain itu, method addRestoranSubmit akan mengembalikan html page add-restoran.html
Jelaskan apa kegunaan dari JPA Repository? JPA Repository digunakan untuk memasukkan objek-objek Java ke dalam sebuah relational database. Kemudian JPA Repo juga dapat digunakan untuk mengatur database tanpa menggunakan query
Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara RestoranModel dan MenuModel dibuat? terdapat @OneToMany dan @ManyToOne yang menunjukkan relasi model-model tersebut. @OneToMany yang ada di RestoranModel menunjukkan bahwa satu restoran dapat memiliki banyak menu, dan @ManyToOne yang ada di MenuModel menunjukkan bahwa banyak menu dapat dimiliki satu restoran.
Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER FetchType.LAZY digunakan saat kita hanya ingin melakukan fetch saat kita membutuhkan datanya. Biasanya, FetchType ini digunakan untuk relasi one-to-many atau many-to-many.
Sebaliknya, FetchType.EAGER digunakan saat kita ingin data yang akan di-fetch sudah ada saat kita membutuhkannya, jadi ia melakukan fetch seawal mungkin. Biasanya, FetchType ini digunakan untuk relasi many-to-one atau one-to-one.

CascadeType.ALL digunakan agar segala perubahan yang terjadi pada suatu entitas akan terjadi juga pada entitas yang memiliki relasi ini dengannya. Perubahan dapat terjadi karena DELETE, UPDATE, dan sebagainya. Sebagai contoh, ketika kita menghapus suatu restoran, maka semua menu yang terkait dengan restoran tersebut juga dapat dihapus.

Tutorial 4
###PERTANYAAN

Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2
Pada latihan nomor 2, saya diharuskan untuk mengubah title pada navbar yang aktif. Pertama saya menambahkan variable di fragment.html. variable tersebut saya beri nama pageTitle. Variable tersebut akan berubah tergantung dengan pageTitle yang dimiliki oleh setiap halaman html yang ada di gopud. Misalkan pada halaman home, saya memberikan nilai variabel pageTitle dengan nama "Home". Nantinya variabel pageTitle yang dituliskan pada fragment.html akan digantikan dengan tulisan 'Home'
Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda menyelesaikan latihan nomor 2
Jelaskan perbedaan th:include dan th:replace
jika kita menggunakan th:include, fragment akan diletakkan didalam tag yg mengantung th:include tersebut. Sedangkan apabila menggunakan th:replace, seluruh isi tag akan digantikan dengan fragment.
Jelaskan bagaimana penggunaan th:object beserta tujuannya th:object berfungsi untuk menjadikan atributnya sebagai object dari operasi-operasi dibawahnya. Misalkan kita menuliskan th:object="${restoran}", maka restoran dapat digunakan sebagai object pada operasi selanjutnya hanya dengan menuliskan tanda bintang.
< span th :text="* {nama}" >kaefci< /span>

< span th :text=" $ {restoran.nama}"> mekdi< /span>.

##Tutorial 5 
###PERTANYAAN

Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and then: Given pada unit test tersebut adalah RestoranModel restoran = generateDummyRestoranModel(1); karena part dari test tersebut merupakan kondisi-kondisi awal inisiasi Test. given tersebut menginisiasi RestoranModel menggunakan method generateDummyRestoran. when(restoranService.getRestoranByIdRestoran(1L)).thenReturn(Optional.of(restoran)); juga merupakan bagian dari given karena membantu proses inisiasi (apa yang harus dilakukan ketika terjadi interaksi tertentu)
bagian When pada unit test yang dibuat adalah bagian code wherestoranService.getRestoranByIdRestoran(1L), karena bagian itulah yang ingin kita test
Sisa dari code test tersebut merupakan bagian dari And Then, karena menampilkan reaksi apa yang kita harapkan. andExpect dan verify merupakan bagian dari andThen

Jelaskan perbedaan line coverage dan logic coverage: Line coverage menghitung persentase line yang dieksekusi oleh unit test. Logic coverage sendiri merupakan jenis testing yang dilakukan untuk masing-masing bagian tertentu, contohnya kondisi if else yang ada di dalam suatu use case.

Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, code atau unit test? Mengapa seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin terjadi?
Seharusnya, yang harus dibuat adalah unit test. Unit Test berfungsi untuk menentukan dari awal standar-standar apa yang seharusnya diterapkan oleh suatu aplikasi. Pada Unit Test, kita juga dapat mengontrol program dengan baik. Selain itu, dengan adanya unit test, proses development akan lebih cepat karena kita sudah menentukan standar-standarnya pada unit test.
Jika kita membuat code lebih dulu dibandingkan unit test, tentu proses development akan lebih lama. Selain itu, kita mungkin kesulitan untuk mencari solusi error yang muncul.

[Bonus] Jelaskan mengapa pada latihan no 3, main class spring tidak diikutsertakan ke dalam perhitungan coverage? Apa saja yang dapat menyebabkan suatu class dapat di-exclude dari perhitungan code coverage.
Karena main class pada project gopud ini hanya bersifat konfigurasi dari project, tidak perlu disertakan pada test coverage. Jika tetap disertakan, perhitungan coverage akan tetap mengitung code main class tersebut. Dengan mengexclude main class, coverage akan lebih dapat terlihat secara detail.

##Screen Capture Coverage 
###MenuServiceTest 
######Before before 
######After after

###RestoranControllerTest before 
######After after

##Tutorial 6
###PERTANYAAN
1. Apa itu postman? Apa kegunaan dari postman?
    -   Postman merupakan sebuah tool yang digunakan oleh para developer untuk 
        pembuatan API dan REST Client atau istilahnya adalah aplikasi yang digunakan untuk
        melakukan uji coba REST API yang telah kita buat. Fungsi utama postman ini adalah 
        sebagai GUI API Caller. Sekarang ini Postman menyediakan fitur lain juga seperti S
        haring Collection API for Documentation , Testing API , Realtime Collaboration Team, 
        Monitoring API, Integration 
        
2. Apa kegunaan dari annotation @JsonIgnoreProperties?
    -   @JsonIgnoreProperties berfungsi sebagai penanda untuk mengabaikan berbagai properti logic
        pada JSON serialization dan deserialization. Anotasi ini digunakan pada class level
        
3.  Apa itu ResponseEntity dan apa kegunaannya?
    -   Response Entity digunakan untuk merepresentasikan keseluruhan bagian HTTP response yang 
        terdiri status code, header, and body. Dengan adanya Response Entity, kita dapat mengkonfigurasi 
        secara menyeluruh. Response Entity merupakan tipe generik.