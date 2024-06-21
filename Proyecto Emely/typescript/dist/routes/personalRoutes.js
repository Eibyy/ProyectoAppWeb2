"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const personalModel_1 = require("../models/personalModel");
const typeorm_1 = require("typeorm");
const router = (0, express_1.Router)();
router.get('/', async (req, res) => {
    const personals = await (0, typeorm_1.getRepository)(personalModel_1.Personal).find({ relations: ["Roles"] });
    res.json(personals);
});
router.post('/', async (req, res) => {
    const personal = (0, typeorm_1.getRepository)(personalModel_1.Personal).create(req.body);
    const result = await (0, typeorm_1.getRepository)(personalModel_1.Personal).save(personal);
    res.json(result);
});
exports.default = router;
