using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TNT.Core.Http.DI;
using TNT.Core.Helpers.DI;
using System.Data.SqlClient;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http.Extensions;
using Microsoft.AspNetCore.Http;
using PetID.ImageClassificationML.Model;
using System.IO;
using System.Net.Mime;
using Microsoft.Net.Http.Headers;
using Microsoft.AspNetCore.Mvc.Formatters;

namespace PetID.ClassifyWebApi.Controllers
{
    [Route("api/pet-id")]
    [ApiController]
    [InjectionFilter]
    public class PetIdController : BaseController
    {
        [Inject]
        private ConsumeModel _consumeModel;

        [Produces(MediaTypeNames.Application.Json, MediaTypeNames.Application.Xml,
            Type = typeof(ModelOutputViewModel))]
        [HttpPost("")]
        public IActionResult Create(IFormFile file, int top_count = 5)
        {
            var bytes = new byte[file.Length];
            using (var ms = new MemoryStream(bytes))
            using (var rs = file.OpenReadStream())
            {
                rs.CopyTo(ms);
            }
            var output = _consumeModel.Predict(new ModelInput
            {
                ImageRaw = bytes
            }, top_count);
            return Ok(output);
        }

    }
}