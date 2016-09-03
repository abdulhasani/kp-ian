-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 30, 2013 at 06:27 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `puskesmas`
--
CREATE DATABASE IF NOT EXISTS `puskesmas` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `puskesmas`;

-- --------------------------------------------------------

--
-- Table structure for table `dbadministrator`
--

CREATE TABLE IF NOT EXISTS `dbadministrator` (
  `idadministrator` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(60) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`idadministrator`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbadministrator`
--

INSERT INTO `dbadministrator` (`idadministrator`, `nama`, `alamat`, `telp`, `username`, `password`) VALUES
(1, 'ADMIN', 'BUMIAYU', '08909000', 'ADMIN', '1'),
(2, 'DOKTER', 'BUMIAYU', '090870457', 'SARAS', '008'),
(3, 'APOTEK', 'BUMIAYU', '085640754300', 'ADI', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `dbdetailperiksa`
--

CREATE TABLE IF NOT EXISTS `dbdetailperiksa` (
  `iddetailperiksa` int(11) NOT NULL,
  `idperiksa` int(11) DEFAULT NULL,
  `idpasien` int(11) DEFAULT NULL,
  `iddokter` int(11) DEFAULT NULL,
  `idobat` int(11) DEFAULT NULL,
  `obatperbiji` int(11) NOT NULL,
  `diagnosa` text NOT NULL,
  `alergiobat` text NOT NULL,
  PRIMARY KEY (`iddetailperiksa`),
  KEY `idperiksa` (`idperiksa`),
  KEY `idpasien` (`idpasien`),
  KEY `iddokter` (`iddokter`),
  KEY `idobat` (`idobat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbdetailperiksa`
--

INSERT INTO `dbdetailperiksa` (`iddetailperiksa`, `idperiksa`, `idpasien`, `iddokter`, `idobat`, `obatperbiji`, `diagnosa`, `alergiobat`) VALUES
(1, 1, 1, 1, 4, 1, 'mencret', 'asas'),
(2, 1, 1, 1, 5, 1, 'mencret', 'asas'),
(3, 1, 1, 1, 6, 1, 'mencret', 'asas'),
(4, 2, 2, 1, 3, 6, 'sasas', 'asas'),
(5, 2, 2, 1, 4, 6, 'sasas', 'asas'),
(6, 2, 2, 1, 5, 6, 'sasas', 'asas'),
(7, 3, 4, 1, 3, 5, 'lara dengkul', 'huhuhuhuh'),
(8, 3, 4, 1, 4, 5, 'lara dengkul', 'huhuhuhuh'),
(9, 3, 4, 1, 5, 5, 'lara dengkul', 'huhuhuhuh');

-- --------------------------------------------------------

--
-- Table structure for table `dbdokter`
--

CREATE TABLE IF NOT EXISTS `dbdokter` (
  `iddokter` int(11) NOT NULL,
  `nama_dokter` varchar(30) NOT NULL,
  `alamat_dokter` varchar(60) NOT NULL,
  `telp_dokter` varchar(15) NOT NULL,
  `biaya` double NOT NULL,
  PRIMARY KEY (`iddokter`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbdokter`
--

INSERT INTO `dbdokter` (`iddokter`, `nama_dokter`, `alamat_dokter`, `telp_dokter`, `biaya`) VALUES
(1, 'SANI', 'BUMIAYU', '087657900', 0),
(2, 'ADI', 'BREBES', '1234', 0);

-- --------------------------------------------------------

--
-- Table structure for table `dbobat`
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
  PRIMARY KEY (`idobat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbobat`
--

INSERT INTO `dbobat` (`idobat`, `nama_obat`, `stok_awal`, `penerimaan`, `persediaan`, `pemakaian`, `permintaan`, `sisa_stok`, `harga_satuan`, `tgl_exp`) VALUES
(1, 'ANTALGIN', 0, 0, 0, 0, 0, 0, 50, '2014-10-24'),
(2, 'BETAMETASON', 0, 0, 0, 0, 0, 0, 50, '2014-10-24'),
(3, 'DEXAMETASON', 0, 0, 0, 0, 0, 0, 50, '2014-10-24'),
(4, 'ANTASID', 0, 0, 0, 0, 0, 0, 50, '2014-10-24'),
(5, 'CTM', 0, 0, 0, 0, 0, 0, 40, '2015-01-05'),
(6, 'susu soda', 12, 2, 23, 0, 0, 23, 2000, '2014-10-29'),
(7, 'biskuit', 5, 0, 5, 0, 0, 5, 1000, '2014-10-29');

-- --------------------------------------------------------

--
-- Table structure for table `dbpasien`
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
  PRIMARY KEY (`idpasien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbpasien`
--

INSERT INTO `dbpasien` (`idpasien`, `nama_pasien`, `umur`, `nama_kk`, `jenis_kelamin`, `alamat_pasien`, `kunjungan`, `unit_tujuan`, `jenis_pelayanan`) VALUES
(1, 'ANDI', 29, 'sano', 'Laki-laki', 'BANYUMAS', 'Lama', 'BPU', 'Gratis'),
(2, 'SISKA', 25, 'tarso', 'Perempuan', 'BREBES', 'Lama', 'BPU', 'Gratis'),
(4, 'dalbo', 25, 'dalban', 'Perempuan', 'BUMIAYU', 'Baru', 'KIA', 'Gratis');

-- --------------------------------------------------------

--
-- Table structure for table `dbpembayaran`
--

CREATE TABLE IF NOT EXISTS `dbpembayaran` (
  `idpembayaran` int(11) NOT NULL,
  `idperiksa` int(11) NOT NULL,
  `bayar_obat` double NOT NULL,
  `bayar_periksa` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`idpembayaran`),
  KEY `idperiksa` (`idperiksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dbperiksa`
--

CREATE TABLE IF NOT EXISTS `dbperiksa` (
  `idperiksa` int(11) NOT NULL,
  `tgl_periksa` date NOT NULL,
  PRIMARY KEY (`idperiksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dbperiksa`
--

INSERT INTO `dbperiksa` (`idperiksa`, `tgl_periksa`) VALUES
(1, '2013-10-30'),
(2, '2013-10-30'),
(3, '2013-10-30');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dbdetailperiksa`
--
ALTER TABLE `dbdetailperiksa`
  ADD CONSTRAINT `dbdetailperiksa_ibfk_1` FOREIGN KEY (`idperiksa`) REFERENCES `dbperiksa` (`idperiksa`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dbdetailperiksa_ibfk_2` FOREIGN KEY (`idpasien`) REFERENCES `dbpasien` (`idpasien`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `dbdetailperiksa_ibfk_3` FOREIGN KEY (`iddokter`) REFERENCES `dbdokter` (`iddokter`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `dbdetailperiksa_ibfk_4` FOREIGN KEY (`idobat`) REFERENCES `dbobat` (`idobat`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `dbpembayaran`
--
ALTER TABLE `dbpembayaran`
  ADD CONSTRAINT `dbpembayaran_ibfk_1` FOREIGN KEY (`idperiksa`) REFERENCES `dbperiksa` (`idperiksa`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
