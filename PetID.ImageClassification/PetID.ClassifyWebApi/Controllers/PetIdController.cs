﻿using System;
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

namespace PetID.ClassifyWebApi.Controllers
{
    [Route("api/pet-id")]
    [ApiController]
    [InjectionFilter]
    public class PetIdController : BaseController
    {
        [Inject]
        private ConsumeModel _consumeModel;

        [HttpPost("")]
        public IActionResult Create(IFormFile file)
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
            });
            return Ok(output);
        }

    }
}