Feature: Pagination Shifting
  Modul: Management - Shifting
  Fitur Pagination

  Background:
    Given Pengguna sudah malakukan login dan pengguna di halaman Shifting

  @Test0006
  Scenario: Verifikasi pengguna dapat mengubah jumlah data per halaman
    When Pengguna klik dropdown dan memilih jumlah data per halaman "25" pada halaman Shifting
    Then Jumlah Absen point yang ditampilkan per halaman berubah menjadi 25 pada halaman Shifting

  @Test0007
  Scenario: Verifikasi pengguna dapat berpindah halaman dengan tombol next dan previous pada halam Shifting
    When Pengguna klik tombol next  pada halaman Shifting
    And Pengguna klik tombol previous pada halaman Shifting
    Then Sistem menampilkan halaman Shifting berikutnya dan mendapatkan data dibaris pertama
    # And Sistem menampilkan halaman Shifting sebelumnya dan mendapatkan data dibaris pertama

  @Test0008
  Scenario: Verifikasi pengguna dapat berpindah halaman Shifting dengan tombol first dan last
    When Pengguna klik tombol last pada halaman Shifting
    And Pengguna klik tombol first pada halaman Shifting
    Then Sistem menampilkan halaman Shifting terakhir serta mendapatkan data dibaris pertama
    # And Sistem menampilkan halaman Shifting pertama serta mendapatkan data dibaris pertama
