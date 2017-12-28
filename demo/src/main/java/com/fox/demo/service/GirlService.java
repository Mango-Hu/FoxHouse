package com.fox.demo.service;
import com.fox.demo.model.Girl;
import com.fox.demo.repository.GirlRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class GirlService {

    @Autowired
    GirlRepository girlRepository;

    class Spec implements Specification<Girl> {

        private Predicate predicate;

        private DataTablesInput input;

        Spec(DataTablesInput input) {
            this.input = input;
        }

        @Override
        public Predicate toPredicate(Root<Girl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            if (this.predicate != null) {
                return predicate;
            }
            List<Predicate> list = Lists.newArrayList();
            if(input.getColumns().size()==0) {
                return (predicate = cb.and(list.toArray(new Predicate[list.size()])));
            }
            String id = input.getColumn("id").getSearch().getValue();
            if (StringUtils.hasText(id)) {
                list.add(cb.equal(root.get("id").as(Integer.class), id));
            }
            String name = input.getColumn("name").getSearch().getValue();
            if (StringUtils.hasText(name)) {
                list.add(cb.equal(root.get("name").as(String.class), name));
            }
            String age = input.getColumn("age").getSearch().getValue();
            if (StringUtils.hasText(age)) {
                list.add(cb.equal(root.get("age").as(Integer.class), age));
            }

            return (predicate = cb.and(list.toArray(new Predicate[list.size()])));
        }
    }

    public DataTablesOutput<Girl> findAll(DataTablesInput input) {
        Specification<Girl> specification = new Spec(input);
        DataTablesOutput<Girl> assetCategoryOutput = generateDataTable(input);
        assetCategoryOutput.setRecordsFiltered(girlRepository.count(specification));
        Page<Girl> page = girlRepository.findAll(specification,
                new PageRequest(input.getStart() / input.getLength(), input.getLength(), Sort.Direction.DESC, "id"));
        assetCategoryOutput.setData(page.getContent());
        return assetCategoryOutput;
    }

    private DataTablesOutput<Girl> generateDataTable(DataTablesInput input) {
        DataTablesOutput<Girl> output = new DataTablesOutput<>();
        output.setRecordsTotal(girlRepository.count());
        output.setDraw(input.getDraw());
        return output;
    }
}
