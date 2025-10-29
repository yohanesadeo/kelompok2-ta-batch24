Feature: Search AbsenPoint
   Modul : Management - Absen Point
   Fitur : Pencarian

  @TC-0001
  Scenario Outline: Verifikasi bahwa pengguna dapat mencari absen point berdasarkan nama
    Given Pengguna sudah login dan berada di halaman Absen Point
    When Masukkan nama "<nama>" yang valid pada kolom pencarian
    And Klik tombol Search
    Then Absen point "<nama>" muncul di baris pertama

    Examples:
      | nama             |
      | DIKA Banjarmasin |

