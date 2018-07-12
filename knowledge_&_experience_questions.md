# The Answer

1. Iya pernah dan sampai sekarang selalu pakai Kotlin. Perbedaannya pada:
- Jumlah baris kode. Jumlah baris kode pada kotlin jauh lebih ringkas sehingga mempercepat penulisan kode
- Fitur. Fitur pada kotlin yg powerful dan membuat saya jatuh cinta adalah extension function karena saya dapat membuat helper code yang mudah diakses 
- Readbility. Membaca algoritma melalui kode kotlin lebih mudah dipahami karena minim boilerplate seperti pada Java.

2. Daftar library yg sering saya pakai adalah: 
- RxJava, Reactive Extensions for the JVM. Membantu saya menerapkan proses asynckronus dan event based programming sehingga membuat kecepatan pengolahan koleksi data menjadi lebih cepat.
- RxBus / eventbus. Membantu saya ketika memerlukan proses komunikasi data dari activity, fragment, dan service.
- GSON. Membantu saya untuk melakukan proses parsing data JSON menjadi bentuk model Plain Old Java Object atau data class dalam kotlin. Membantu saya dalam membentuk data JSON dari bentuk data class.
- Retrofit. Membantu saya dalam melakukan proses networking / request API ke server.
- EasyPrefs. Membantu saya dalam mengolah data Shared Preference.
- Mosby. Membantu saya dalam menerapkan pola Model View Presenter. Saya tinggal bikin masing" file lalu melakukan extend
- Realm. Saya selalu mengandalkan ini dalam membuat content provider karena kemudahan dan kecepatan dalam melakukan query data.
- Fresco. Library untuk fetching gambar dengan dukungan yang powerful. Lib ini mengurus proses image loading dari url dan menampilkannya. Kustomisasi bentuk view nya bervariasi.
- com.android.support:appcompat. Library ini sebagai resource UI dengan dukungan kompatibilitas pada android OS dibawah API 21 / sebelum LOLLIPOP
- com.android.support:design. Library untuk menerapkan UI pattern Material Design pada aplikasi android
- com.android.support:recyclerview. Saya selalu pakai library ini dalam menampikan koleksi data, baik dalam bentuk list maupun grid

3. Ya saya gemar menerapkan clean code dengan menggunakan pattern MVP. Alasan saya adalah membantu saya saat trace error / debugging. Pemisahan layer membuat kode yang saya tulis jadi tertata sehingga memudahkan saya ketika melakukan maintenance dan mengembangkan fitur baru. Percuma jika menulis kode tapi tidak merapikannya karena pasti akan merepotkan ketika debugging / maintenance serta akan melakukan pengembangan fitur baru.

4. Tantangan terbesar adalah ketika aplikasi harus mendukung di setiap versi OS Android yang jumlah user nya diatas 10%. Solusi pemecahan masalah dari sisi engineering adalah dengan menerapkan best practice yang direkomendasikan oleh Google atau mayoritas senior developer.

5. Workflow saya mulai dengan mengumpulkan kebutuhan fungsional yang akan diterapkan pada aplikasi. Saya menyusun task list serta fitur yang akan saya kerjakan. Ketika setiap fitur telah selesai dikembangkan, saya akan merilis aplikasi versi development dengan memanfaatkan fitur distribusi pada Crashlytics. Saya dapat menerima laporan error crash dan bug dari fitur tersebut sehingga membuat proses debugging menjadi lebih tepat dan akurat. 

6. Penerapan reactive programming dan design pattern wajib digunakan agar dapat meningkatkan performa aplikasi. Soal security saya melakukan enkripsi untuk setiap key yg digunakan seperti pada SharedPreference, intent extras. Juga menerapkan proguard ketika akan mengenerate aplikasi versi production yg akan dirilis ke Play Store agar source code tidak terbaca ketika ada orang yg tidak bertanggung jawab berusaha mengekstrak apk nya.

7. Tentu saya bersedia On Site di Malang.
