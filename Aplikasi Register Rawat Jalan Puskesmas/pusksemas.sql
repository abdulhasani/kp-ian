-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Waktu pembuatan: 31. Oktober 2013 jam 07:54
-- Versi Server: 5.0.67
-- Versi PHP: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `puskesmas`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbadministrator`
--

CREATE TABLE IF NOT EXISTS `dbadministrator` (
  `idadministrator` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(60) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `bagian` varchar(100) NOT NULL,
  PRIMARY KEY  (`idadministrator`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbadministrator`
--

INSERT INTO `dbadministrator` (`idadministrator`, `nama`, `alamat`, `telp`, `username`, `password`, `bagian`) VALUES
(1, 'sani', 'indonesia', '085747170901', 'sani', 'ganteng', 'Admin'),
(2, 'dalban', 'merdeka', '6789234', 'yoi', 'iyo', 'Loket'),
(3, 'Tegar', 'Cilacap', '0899999', 'tegar', 'tom-tom', 'Periksa'),
(4, 'mamaet', 'brebas', '0897080089', 'metmet', '1234', 'Apotek');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbdetailperiksa`
--

CREATE TABLE IF NOT EXISTS `dbdetailperiksa` (
  `iddetailperiksa` int(11) NOT NULL,
  `idperiksa` int(11) default NULL,
  `idpasien` int(11) default NULL,
  `iddokter` int(11) default NULL,
  `idobat` int(11) default NULL,
  `obatperbiji` int(11) NOT NULL,
  `diagnosa` text NOT NULL,
  `alergiobat` text NOT NULL,
  PRIMARY KEY  (`iddetailperiksa`),
  KEY `idperiksa` (`idperiksa`),
  KEY `idpasien` (`idpasien`),
  KEY `iddokter` (`iddokter`),
  KEY `idobat` (`idobat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbdetailperiksa`
--

INSERT INTO `dbdetailperiksa` (`iddetailperiksa`, `idperiksa`, `idpasien`, `iddokter`, `idobat`, `obatperbiji`, `diagnosa`, `alergiobat`) VALUES
(1, 1, 1, 1, 1, 5, 'bbb', 'bbb'),
(2, 1, 1, 1, 2, 5, 'bbb', 'bbb');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbdokter`
--

CREATE TABLE IF NOT EXISTS `dbdokter` (
  `iddokter` int(11) NOT NULL,
  `nama_dokter` varchar(30) NOT NULL,
  `alamat_dokter` varchar(60) NOT NULL,
  `telp_dokter` varchar(15) NOT NULL,
  `biaya` double NOT NULL,
  PRIMARY KEY  (`iddokter`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbdokter`
--

INSERT INTO `dbdokter` (`iddokter`, `nama_dokter`, `alamat_dokter`, `telp_dokter`, `biaya`) VALUES
(1, 'SANI', 'BUMIAYU', '087657900', 5000),
(2, 'ADI', 'BREBES', '1234', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbobat`
--

CREATE TABLE IF NOT EXISTS `dbobat` (
  `idobat` int(11) NOT NULL,
  `nama_obat` varchar(30) NOT NULL,
  `stok_awal` int(11) NOT NULL,
  `penerimaan` int(11) NOT NULL,
  `persediaan` int(11) NOT NULL,
  `pemakaian` int(11) NOT NULL,
  `permintaan` int(11) NOT NULL,
  `sisa_stok` int(11) NOT NULL,
  `harga_satuan` double NOT NULL,
  `tgl_exp` date NOT NULL,
  PRIMARY KEY  (`idobat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbobat`
--

INSERT INTO `dbobat` (`idobat`, `nama_obat`, `stok_awal`, `penerimaan`, `persediaan`, `pemakaian`, `permintaan`, `sisa_stok`, `harga_satuan`, `tgl_exp`) VALUES
(1, 'Percobaan', 10, 10, 20, 10, 10, 10, 100, '2014-10-30'),
(2, 'Bodrex', 10, 5, 15, 5, 5, 10, 105, '2014-10-30');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbpasien`
--

CREATE TABLE IF NOT EXISTS `dbpasien` (
  `idpasien` int(11) NOT NULL,
  `nama_pasien` varchar(30) NOT NULL,
  `umur` int(11) NOT NULL,
  `nama_kk` varchar(60) NOT NULL,
  `jenis_kelamin` varchar(60) NOT NULL,
  `alamat_pasien` varchar(60) NOT NULL,
  `kunjungan` varchar(60) NOT NULL,
  `unit_tujuan` varchar(60) NOT NULL,
  `jenis_pelayanan` varchar(60) NOT NULL,
  PRIMARY KEY  (`idpasien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbpasien`
--

INSERT INTO `dbpasien` (`idpasien`, `nama_pasien`, `umur`, `nama_kk`, `jenis_kelamin`, `alamat_pasien`, `kunjungan`, `unit_tujuan`, `jenis_pelayanan`) VALUES
(1, 'ANDI', 29, 'sano', 'Laki-laki', 'BANYUMAS', 'Lama', 'BPU', 'Gratis'),
(2, 'SISKA', 25, 'tarso', 'Perempuan', 'BREBES', 'Lama', 'BPU', 'Gratis'),
(4, 'dalbo', 25, 'dalban', 'Perempuan', 'BUMIAYU', 'Baru', 'KIA', 'Gratis');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbpembayaran`
--

CREATE TABLE IF NOT EXISTS `dbpembayaran` (
  `idpembayaran` int(11) NOT NULL,
  `idperiksa` int(11) NOT NULL,
  `bayar_obat` double NOT NULL,
  `bayar_periksa` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY  (`idpembayaran`),
  KEY `idperiksa` (`idperiksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbpembayaran`
--

INSERT INTO `dbpembayaran` (`idpembayaran`, `idperiksa`, `bayar_obat`, `bayar_periksa`, `total`) VALUES
(1, 1, 1025, 5000, 6025);

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbperiksa`
--

CREATE TABLE IF NOT EXISTS `dbperiksa` (
  `idperiksa` int(11) NOT NULL,
  `tgl_periksa` date NOT NULL,
  PRIMARY KEY  (`idperiksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dbperiksa`
--

INSERT INTO `dbperiksa` (`idperiksa`, `tgl_periksa`) VALUES
(1, '2013-10-30');

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `dbdetailperiksa`
--
ALTER TABLE `dbdetailperiksa`
  ADD CONSTRAINT `dbdetailperiksa_ibfk_1` FOREIGN KEY (`idperiksa`) REFERENCES `dbperiksa` (`idperiksa`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dbdetailperiksa_ibfk_2` FOREIGN KEY (`idpasien`) REFERENCES `dbpasien` (`idpasien`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `dbdetailperiksa_ibfk_3` FOREIGN KEY (`iddokter`) REFERENCES `dbdokter` (`iddokter`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `dbdetailperiksa_ibfk_4` FOREIGN KEY (`idobat`) REFERENCES `dbobat` (`idobat`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `dbpembayaran`
--
ALTER TABLE `dbpembayaran`
  ADD CONSTRAINT `dbpembayaran_ibfk_1` FOREIGN KEY (`idperiksa`) REFERENCES `dbperiksa` (`idperiksa`) ON DELETE CASCADE ON UPDATE CASCADE;
