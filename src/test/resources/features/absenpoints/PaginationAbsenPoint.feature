Feature: Pagination Absen Point
  Modul: Management - Shifting
  Fitur Pagination

  Background:
   Given Pengguna sudah berada malakukan login dan pengguna di halaman Absen Point

  @Test0007
  Scenario: Verifikasi pengguna dapat mengubah jumlah data per halaman
    When Pengguna klik dropdown dan memilih jumlah data per halaman "25"
    Then Jumlah Absen point yang ditampilkan per halaman berubah menjadi 25

  @Test0008
  Scenario: Verifikasi pengguna dapat berpindah halaman dengan tombol next dan previous
    When Pengguna klik tombol next
    And Pengguna klik tombol previous
    Then Sistem menampilkan halaman berikutnya dan mendapatkan data dibaris pertama
    # And Sistem menampilkan halaman sebelumnya dan mendapatkan data dibaris pertama

  @Test00010
  Scenario: Verifikasi pengguna dapat berpindah halaman dengan tombol first dan last
    When Pengguna klik tombol last
    And Pengguna klik tombol first
    Then Sistem menampilkan halaman terakhir serta mendapatkan data dibaris pertama
    # And Sistem menampilkan halaman pertama serta mendapatkan data dibaris pertama

