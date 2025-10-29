Feature: Hapus Shifting
   Modul : Management - Shifting
   Fitur : Hapus

  @TC-0005
  Scenario Outline: Verifikasi bahwa pengguna dapat menghapus shifting bernama "Budi".
    Given Pengguna sudah login dan berada dihalaman Shifting
    When Pengguna melakukan search pada bagian halaman Shifting "<nama>"
    And Pengguna klik ikon titik tiga pada halaman Shifting di sebelah kanan dan memilih opsi Hapus
    And Pengguna mendapatkan konfirmasi Ya penghapusan
    Then Pengguna mendapatkan popup berisi "<message>"

    Examples:
      | nama | message                  |
      | Andi | Berhasil Delete Shifting |
