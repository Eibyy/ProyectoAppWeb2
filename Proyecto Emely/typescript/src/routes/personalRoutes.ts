import { Router } from 'express';
import { Personal } from '../models/personalModel';
import { getRepository } from 'typeorm';

const router = Router();

router.get('/', async (req, res) => {
  const personals = await getRepository(Personal).find({ relations: ["Roles"] });
  res.json(personals);
});

router.post('/', async (req, res) => {
  const personal = getRepository(Personal).create(req.body);
  const result = await getRepository(Personal).save(personal);
  res.json(result);
});

export default router;
