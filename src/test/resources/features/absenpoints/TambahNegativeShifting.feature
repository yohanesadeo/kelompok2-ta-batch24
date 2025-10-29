Feature: Tambah Location Point - Negative Test
  Modul: Management - Absen Point

  Background:
    Given Pengguna sudah login ke aplikasi dan pengguna berada di halaman Absen Point
    And Pengguna membuka form Tambah Location Point

  @TC-0010
  Scenario: Gagal tambah location point karena Nama kosong
    When Pengguna mengisi data kosongkan "<nama>", "<Latitude>", "<Longitude>", "<MaksimalRadius>", "<Deskripsi>" dan klik tambah
    Then Sistem menampilkan pesan error "<message>" pada field Nama

    Examples:
      | nama | Latitude         | Longitude          | MaksimalRadius | Deskripsi     | message                     |
      |      | -6.2087653591721 | 106.84553626670834 |            100 | Test Location | Please fill out this field. |

  @TC-0011
  Scenario: Gagal tambah location point karena Latitude kosong
    When Pengguna mengisi data "<nama>", kosongkan bagian "<Latitude>", "<Longitude>", "<MaksimalRadius>", "<Deskripsi>" dan klik tambah
    Then Sistem menampilkan pesan error "<message>" pada field Latitude

    Examples:
      | nama     | Latitude | Longitude          | MaksimalRadius | Deskripsi     | message                     |
      | Lokasi 1 |          | 106.84553626670834 |            100 | Test Location | Please fill out this field. |

  @TC-0012
  Scenario: Gagal tambah location point karena Longitude kosong
    When Pengguna mengisi data "<nama>", "<Latitude>", kosongkan bagian "<Longitude>", "<MaksimalRadius>", "<Deskripsi>" dan klik tambah
    Then Sistem menampilkan pesan error "<message>" pada field Longitude

    Examples:
      | nama     | Latitude         | Longitude | MaksimalRadius | Deskripsi     | message                     |
      | Lokasi 1 | -6.2087653591721 |           |            100 | Test Location | Please fill out this field. |

  @TC-0013
  Scenario: Gagal tambah location point karena Radius kosong
    When Pengguna mengisi data "<nama>", "<Latitude>", "<Longitude>", kosongkan bagian "<MaksimalRadius>", "<Deskripsi>" dan klik tambah
    Then Sistem menampilkan pesan error "<message>" pada field Radius

    Examples:
      | nama     | Latitude         | Longitude          | MaksimalRadius | Deskripsi     | message                |
      | Lokasi 1 | -6.2087653591721 | 106.84553626670834 |                | Test Location | Please enter a number. |
