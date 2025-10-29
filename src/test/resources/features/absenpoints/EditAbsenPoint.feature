Feature: Edit Absen Point
  Modul : Management - Absen Point

  @TC-0005
  Scenario Outline: Verifikasi bahwa pengguna dapat mengedit informasi absen point
    Given Pengguna sudah melakukan login dan pengguna berada di halaman Absen Point
    And Absen point "DKA Jakarta" sudah ada
    When Pengguna mencari absen point "<Nama>"
    And Pengguna klik ikon titik tiga pada absen point dan memilih opsi Edit
    And Pengguna mengubah deskripsi menjadi "<Deskripsi>"
    And Pengguna klik tombol Simpan
    Then Muncul popup konfirmasi menandakan "<PopupMessage>"

    Examples:
      | Nama        | Deskripsi                                                                               | PopupMessage                 |
      | DKA Jakarta | Jl. Komplek Pertokoan Golden City Blok C No 5, Bengkong Laut Batam 29458 (Diperbaharui) | Berhasil Edit Location Point |
