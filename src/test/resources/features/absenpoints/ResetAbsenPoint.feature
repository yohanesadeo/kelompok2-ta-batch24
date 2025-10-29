Feature: Reset Pencarian AbsenPoint
  Modul : Management - Absen Point
  Fitur : Reset

  @TC-0002
  Scenario: Verifikasi bahwa pengguna dapat mereset kolom pencarian
    Given Pengguna melakukan login dan pegguna sudah berada di halaman Absen Point
    When Masukkan nama "DKA Banjarmasin" pada kolom pencarian
    And Klik tombol Reset
    Then Kolom pencarian kosong
