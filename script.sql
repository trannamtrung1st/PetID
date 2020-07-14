USE [master]
GO
/****** Object:  Database [PetID]    Script Date: 7/14/2020 10:04:16 PM ******/
CREATE DATABASE [PetID]
GO
ALTER DATABASE [PetID] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PetID].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PetID] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PetID] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PetID] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PetID] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PetID] SET ARITHABORT OFF 
GO
ALTER DATABASE [PetID] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PetID] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PetID] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PetID] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PetID] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PetID] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PetID] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PetID] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PetID] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PetID] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PetID] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PetID] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PetID] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PetID] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PetID] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PetID] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PetID] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PetID] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PetID] SET  MULTI_USER 
GO
ALTER DATABASE [PetID] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PetID] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PetID] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PetID] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PetID] SET DELAYED_DURABILITY = DISABLED 
GO
USE [PetID]
GO
/****** Object:  Table [dbo].[BreedAttr]    Script Date: 7/14/2020 10:04:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BreedAttr](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[breedCode] [varchar](50) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[value] [nvarchar](255) NULL,
 CONSTRAINT [PK_BreedAttr] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BreedInfo]    Script Date: 7/14/2020 10:04:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BreedInfo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[breedCode] [varchar](50) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[sectionContent] [ntext] NULL,
 CONSTRAINT [PK_BreedInfo] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BreedTrait]    Script Date: 7/14/2020 10:04:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BreedTrait](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[breedCode] [varchar](50) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[value] [nvarchar](255) NULL,
 CONSTRAINT [PK_BreedTrait] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PetBreed]    Script Date: 7/14/2020 10:04:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PetBreed](
	[code] [varchar](50) NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[url] [nvarchar](500) NULL,
	[imageUrl] [nvarchar](2000) NULL,
	[availableUrl] [nvarchar](2000) NULL,
	[isAvailableParsed] [bit] NULL,
	[isBreedImagesParsed] [bit] NULL,
	[typeName] [nvarchar](50) NOT NULL,
	[dogilyCodeMapping] [varchar](50) NULL,
 CONSTRAINT [PK_PetBreed] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PetPost]    Script Date: 7/14/2020 10:04:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PetPost](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[imageUrl] [nvarchar](2000) NULL,
	[detailUrl] [nvarchar](1000) NULL,
	[petBreedCode] [varchar](50) NOT NULL,
	[available] [bit] NULL,
 CONSTRAINT [PK_PetPost] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PetType]    Script Date: 7/14/2020 10:04:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PetType](
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NULL,
 CONSTRAINT [PK_PetKind] PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_BreedAttr]    Script Date: 7/14/2020 10:04:16 PM ******/
CREATE NONCLUSTERED INDEX [IX_BreedAttr] ON [dbo].[BreedAttr]
(
	[breedCode] ASC,
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_BreedInfo]    Script Date: 7/14/2020 10:04:16 PM ******/
CREATE NONCLUSTERED INDEX [IX_BreedInfo] ON [dbo].[BreedInfo]
(
	[breedCode] ASC,
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_BreedTrait]    Script Date: 7/14/2020 10:04:16 PM ******/
CREATE NONCLUSTERED INDEX [IX_BreedTrait] ON [dbo].[BreedTrait]
(
	[breedCode] ASC,
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BreedAttr]  WITH CHECK ADD  CONSTRAINT [FK_BreedAttr_PetBreed] FOREIGN KEY([breedCode])
REFERENCES [dbo].[PetBreed] ([code])
GO
ALTER TABLE [dbo].[BreedAttr] CHECK CONSTRAINT [FK_BreedAttr_PetBreed]
GO
ALTER TABLE [dbo].[BreedInfo]  WITH CHECK ADD  CONSTRAINT [FK_BreedInfo_PetBreed] FOREIGN KEY([breedCode])
REFERENCES [dbo].[PetBreed] ([code])
GO
ALTER TABLE [dbo].[BreedInfo] CHECK CONSTRAINT [FK_BreedInfo_PetBreed]
GO
ALTER TABLE [dbo].[BreedTrait]  WITH CHECK ADD  CONSTRAINT [FK_BreedTrait_PetBreed] FOREIGN KEY([breedCode])
REFERENCES [dbo].[PetBreed] ([code])
GO
ALTER TABLE [dbo].[BreedTrait] CHECK CONSTRAINT [FK_BreedTrait_PetBreed]
GO
ALTER TABLE [dbo].[PetBreed]  WITH CHECK ADD  CONSTRAINT [FK_PetBreed_PetType] FOREIGN KEY([typeName])
REFERENCES [dbo].[PetType] ([name])
GO
ALTER TABLE [dbo].[PetBreed] CHECK CONSTRAINT [FK_PetBreed_PetType]
GO
ALTER TABLE [dbo].[PetPost]  WITH CHECK ADD  CONSTRAINT [FK_PetPost_PetBreed] FOREIGN KEY([petBreedCode])
REFERENCES [dbo].[PetBreed] ([code])
GO
ALTER TABLE [dbo].[PetPost] CHECK CONSTRAINT [FK_PetPost_PetBreed]
GO
USE [master]
GO
ALTER DATABASE [PetID] SET  READ_WRITE 
GO
