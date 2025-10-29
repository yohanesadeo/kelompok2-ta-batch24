Feature: Hapus AbsenPoint
   Modul : Management - Absen Point
   Fitur : Hapus

  @TC-0006
  Scenario Outline: Verifikasi bahwa pengguna dapat menghapus absen point "DKA Bengkulu"
    Given Pengguna sudah melakukan login dan pengguna sudah di halaman Absen Point
    When Pengguna mencari atau search absen point "<nama>"
    And Pengguna klik ikon titik tiga di sebelah kanan dan memilih opsi Hapus
    And Pengguna konfirmasi penghapusan
    Then Pengguna mendapatkan popup "<message>"

    Examples:
      | nama         | message                       |
      | DKA Bengkulu | Berhasil Delete Location Point |
