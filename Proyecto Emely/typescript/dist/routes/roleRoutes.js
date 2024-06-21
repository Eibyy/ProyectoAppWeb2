"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const roleModel_1 = require("../models/roleModel");
const typeorm_1 = require("typeorm");
const router = (0, express_1.Router)();
router.get('/', async (req, res) => {
    const roles = await (0, typeorm_1.getRepository)(roleModel_1.Roles).find();
    res.json(roles);
});
router.post('/', async (req, res) => {
    const role = (0, typeorm_1.getRepository)(roleModel_1.Roles).create(req.body);
    const result = await (0, typeorm_1.getRepository)(roleModel_1.Roles).save(role);
    res.json(result);
});
exports.default = router;
