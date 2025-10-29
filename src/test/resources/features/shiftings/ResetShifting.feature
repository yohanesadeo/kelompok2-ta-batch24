Feature: Reset Pencarian Shifting
  Modul : Management - Shifting
  Fitur : Pencarian

  @TC-0002
  Scenario: Verifikasi bahwa pengguna dapat mereset kolom pencarian
    Given Pengguna melakukan login dan pegguna sudah berada di halaman Shifting
    When Masukkan nama Shifting "<nama>" pada kolom pencarian
    And Klik tombol Reset pada halaman Shifting
    Then Kolom pencarian kosong pada halaman Shifting

    Examples:
      | nama |
      | Andi |
