Feature: Edit Shifting
   Modul : Management - Shifting
   Fitur : Edit

   Background:
   Given Pengguna sudah melakukan login dan berada di halaman Shifting
   And Shifting bernama "Budi" sudah ada

  @TC-0004
  Scenario Outline: Verifikasi bahwa pengguna dapat mengedit kode Shifting.
    When Pengguna mencari "<nama>" di bagian search pada halaman shifting
    And Pengguna klik ikon titik tiga pada halaman Shifting di sebelah kanan dan memilih opsi Edit
    And Pengguna mengubah kode shifting "<kode>"
    And Pengguna melakukan klik tombol simpan 
    Then Cek pada tabel shifting bernama "<nama>" untuk melihat apakah kode shifting "<kode>" sudah terganti

    Examples:
      | nama | kode       |
      | Andi | TestAB2324QA |
