Feature: Tambah Shifting - Positif Test
  Modul: Management - Shifting
  Fitur: Tambah

  @TC-0003
  Scenario Outline: Verifikasi bahwa pengguna dapat menambahkan Shifting baru
    Given Pengguna sudah melakukan login dan pengguna sudah berada di halaman Shifting
    When Pengguna melakukan klik tombol Tambahkan pada halaman Shifting
    And Pengguna mengisi data "<nama>", "<pilihUnit>", "<jamMasuk>", <menitMasuk>, "<jamKeluar>", <menitKeluar>, "<codeShifting>", "<jamBreakStart>", <menitBreakStart>, "<jamBreakEnd>", <menitBreakEnd> dan klik simpan
    Then Data berhasil ditambahkan dengan validasi bahwa "<nama>" telah muncul pada tabel halaman Shifting

    Examples:
      | nama  | pilihUnit                 | jamMasuk | menitMasuk | jamKeluar | menitKeluar | codeShifting | jamBreakStart | menitBreakStart | jamBreakEnd | menitBreakEnd |
      | Andi | BCA Biro Keuangan dan SDM |        8 |         10 |        16 |          36 | TestAB2324QA     |            13 |              10 |          14 |            15 |
