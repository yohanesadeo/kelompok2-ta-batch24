Feature: Tambah Location Point - Negative Test
  Modul: Management - Absen Point

  Background:
    Given Pengguna sudah login ke aplikasi dan pengguna berada di halaman Shifting
    And Pengguna mengisi form Tambah Shifting
  @TC-0009
  Scenario: Gagal tambah shifting karena Nama kosong
    When Pengguna melakukan pengisian data, kosongkan "<nama>", "<pilihUnit>", "<jamMasuk>", <menitMasuk>, "<jamKeluar>", <menitKeluar>, "<codeShifting>", "<jamBreakStart>", <menitBreakStart>, "<jamBreakEnd>", <menitBreakEnd> dan klik simpan
    Then Sistem menampilkan pesan error "<message>" pada field Nama pada halaman shifting
    Examples:
      | nama | pilihUnit                 | jamMasuk | menitMasuk | jamKeluar | menitKeluar | codeShifting | jamBreakStart | menitBreakStart | jamBreakEnd | menitBreakEnd | message                    |
      |      | BCA Biro Keuangan dan SDM |        8 |         10 |        16 |          36 | AE87236BQ    |            13 |              10 |          14 |            15 | Nama shifting harus diisi! |
  
  # @TC-0010
  # Scenario: Gagal tambah shifting karena Jam Masuk kosong
  #   When Pengguna melakukan pengisian data "<nama>", "<pilihUnit>", "<jamKeluar>", <menitKeluar>, "<codeShifting>", "<jamBreakStart>", <menitBreakStart>, "<jamBreakEnd>", <menitBreakEnd> dan klik simpan
  #   Then Sistem menampilkan pesan error "<message>" pada field Jam Masuk
  #   Examples:
  #     | nama | pilihUnit                 | jamMasuk | menitMasuk | jamKeluar | menitKeluar | codeShifting | jamBreakStart | menitBreakStart | jamBreakEnd | menitBreakEnd | message                |
  #     | Tono | BCA Biro Keuangan dan SDM |          |            |        16 |          36 | AE87236BQ    |            13 |              10 |          14 |            15 | Jam masuk harus diisi! |

  @TC-0011
  Scenario: Gagal tambah shifting karena Code Shifting kosong
    When Pengguna melakukan pengisian data "<nama>", "<pilihUnit>", "<jamMasuk>", <menitMasuk>, "<jamKeluar>", <menitKeluar>, kosongkan "<codeShifting>", "<jamBreakStart>", <menitBreakStart>, "<jamBreakEnd>", <menitBreakEnd> dan klik simpan
    Then Sistem menampilkan pesan error "<message>" pada field Code Shifting

    Examples:
      | nama | pilihUnit                 | jamMasuk | menitMasuk | jamKeluar | menitKeluar | codeShifting | jamBreakStart | menitBreakStart | jamBreakEnd | menitBreakEnd | message                    |
      | Tono | BCA Biro Keuangan dan SDM |        8 |         10 |        16 |          36 |              |            13 |              10 |          14 |            15 | Kode Shifting Harus Diisi! |
