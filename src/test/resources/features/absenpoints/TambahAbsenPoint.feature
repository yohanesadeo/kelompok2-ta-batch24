Feature: Tambah Absen Point - Positif Test
  Modul: Management - Absen Point
  Fitur: Tambah

  @TC-0003
  Scenario Outline: Verifikasi bahwa pengguna dapat menambahkan absen point baru
    Given Pengguna sudah melakukan login dan berada di halaman Absen Point
    When Pengguna klik tombol Tambahkan
    And Pengguna mengisi data "<nama>", <Latitude>, <Longitude>, <MaksimalRadius>, "<Deskripsi>" dan klik simpan
    Then Muncul popup konfirmasi "<popup>"

    Examples:
      | nama        | Latitude         | Longitude          | MaksimalRadius | Deskripsi                        | popup                          |
      | DKA Bekasi | -6.2087653591721 | 106.84553626670834 |            100 | Jl. Sudirman No.1, Jakarta Pusat | Berhasil Tambah Location Point |

  @TC-0004
  Scenario Outline: Verifikasi bahwa pengguna dapat menambahkan absen point baru
    Given Pengguna sudah melakukan login dan berada di halaman Absen Point
    When lalu Pengguna klik tombol Tambahkan
    And Pengguna mengisi data serupa "<nama>", <Latitude>, <Longitude>, <MaksimalRadius>, "<Deskripsi>" dan klik simpan lagi
    Then Muncul popup konfirmasi bahwa "<popup>"

    Examples:
      | nama        | Latitude         | Longitude          | MaksimalRadius | Deskripsi                        | popup                          |
      | DKA Bekasi | -6.2087653591721 | 106.84553626670834 |            100 | Jl. Sudirman No.1, Jakarta Pusat | Berhasil Tambah Location Point |

 
