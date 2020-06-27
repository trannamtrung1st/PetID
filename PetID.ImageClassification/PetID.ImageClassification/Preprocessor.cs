using PetID.ImageClassificationML.Model;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;

namespace PetID.ImageClassification
{
    public class Preprocessor
    {

        public static void LoadDatasetAndSaveToTsv(string datasetPath, string tsvPath)
        {
            var files = Directory.GetFiles(datasetPath, "*",
                searchOption: SearchOption.AllDirectories);
            var trainingData = new List<TrainingModel>();
            foreach (var file in files)
            {
                if ((Path.GetExtension(file) != ".jpg") && (Path.GetExtension(file) != ".png"))
                    continue;
                var label = Directory.GetParent(file).Name;
                trainingData.Add(new TrainingModel
                {
                    Label = label,
                    ImageSource = file
                });
            }
            var tsv = new StringBuilder("");
            foreach (var d in trainingData)
            {
                tsv.AppendLine($"{d.Label}\t{d.ImageSource}");
            }
            File.WriteAllText(tsvPath, tsv.ToString());
        }

        public static void FlipAll(string rawFolderPath, bool all = false)
        {
            var files = Directory.GetFiles(rawFolderPath, "*",
               searchOption: all ? SearchOption.AllDirectories : SearchOption.TopDirectoryOnly);
            foreach (var f in files)
            {
                var file = new FileInfo(f);
                using (var img = Image.FromFile(f))
                {
                    img.RotateFlip(RotateFlipType.RotateNoneFlipX);
                    img.Save(Path.Combine(file.DirectoryName, $"flipped_{file.Name}"));
                }
            }
        }

        public static void DeleteAllRaw(string rawFolderPath, bool all = false)
        {
            var files = Directory.EnumerateFiles(rawFolderPath, "*",
                  searchOption: all ? SearchOption.AllDirectories : SearchOption.TopDirectoryOnly)
                .Select(f => new FileInfo(f))
                .Where(f => !f.Name.StartsWith("resized_")).ToList();
            foreach (var f in files)
            {
                f.Delete();
            }
        }

        public static void ResizeAll(string rawFolderPath, bool all = false)
        {
            var files = Directory.GetFiles(rawFolderPath, "*",
                  searchOption: all ? SearchOption.AllDirectories : SearchOption.TopDirectoryOnly);
            foreach (var f in files)
            {
                var file = new FileInfo(f);
                using (var img = Image.FromFile(f))
                {
                    var bm = GraphicHelper.ResizeImage(img, 160, 90);
                    //img.Dispose();
                    bm.Save(Path.Combine(file.DirectoryName, $"resized_{file.Name}"));
                }
            }
        }

    }
}
